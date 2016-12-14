package pe.comercio.materialsearchview.view.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pe.comercio.materialsearchview.R;
import pe.comercio.materialsearchview.activity.FirstDemoActivity;
import pe.comercio.materialsearchview.activity.SecondDemoActivity;
import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.SecondFilter;
import pe.comercio.materialsearchview.view.UserFilter;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.UserVH> implements Filterable{

    List<UserEntity> originalUserList;
    List<UserEntity> filteredUserList;
    private SecondFilter secondFilter;

    SecondDemoActivity firstDemoActivity;

    public SecondAdapter(SecondDemoActivity firstDemoActivity, List<UserEntity> originalUserList) {
        this.originalUserList = originalUserList;
        this.filteredUserList = originalUserList;
        this.firstDemoActivity = firstDemoActivity;

    }

    @Override
    public UserVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_contact, parent, false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(UserVH holder, int position) {
        holder.lblName.setText(filteredUserList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }

    @Override
    public Filter getFilter() {
        if (secondFilter == null) {
            secondFilter = new SecondFilter(originalUserList, this);
        }
        return secondFilter;
    }

    class UserVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView lblName;
        UserVH(View itemView) {
            super(itemView);
            lblName = (AppCompatTextView) itemView.findViewById(R.id.lblName);

            lblName.setOnClickListener(this);
            lblName.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lblName:
                    Toast.makeText(firstDemoActivity, "" +getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }

        @Override
        public boolean onLongClick(View view) {
            switch (view.getId()){
                case R.id.lblName:
                    firstDemoActivity.showDeleteLabelDialogFragment(filteredUserList.get(getAdapterPosition()).getName(), getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public List<UserEntity> getFilteredList(){
        return filteredUserList;
    }
}