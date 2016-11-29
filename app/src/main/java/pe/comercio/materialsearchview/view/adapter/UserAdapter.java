package pe.comercio.materialsearchview.view.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import pe.comercio.materialsearchview.R;
import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.UserFilter;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> implements Filterable{

    List<UserEntity> originalUserList;
    List<UserEntity> filteredUserList;
    private UserFilter userFilter;

    Context mContext;

    public UserAdapter(Context context, List<UserEntity> originalUserList) {
        this.originalUserList = originalUserList;
        this.filteredUserList = originalUserList;
        this.mContext = context;

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
        if (userFilter == null) {
            userFilter = new UserFilter(originalUserList, this);
        }
        return userFilter;
    }

    class UserVH extends RecyclerView.ViewHolder {
        TextView lblName;
        UserVH(View itemView) {
            super(itemView);
            lblName = (AppCompatTextView) itemView.findViewById(R.id.lblName);
        }
    }

    public List<UserEntity> getFilteredList(){
        return filteredUserList;
    }
}