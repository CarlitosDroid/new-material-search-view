package pe.comercio.materialsearchview.view;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.adapter.UserAdapter;

/**
 * Created by Carlos Vargas on 11/25/16.
 * CarlitosDroid
 */

public class UserFilter extends Filter {
    private List<UserEntity> originalUserEntityList;
    private List<UserEntity> filteredUserEntityList;
    private UserAdapter adapter;

    public UserFilter(List<UserEntity> originalUserEntityList, UserAdapter adapter) {
        super();
        this.originalUserEntityList = new LinkedList<>(originalUserEntityList);
        this.adapter = adapter;
        this.filteredUserEntityList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        //here you need to add proper items do filteredUserEntityList
        Log.e("charsequence","charsequence "  + constraint);
        filteredUserEntityList.clear();
        final FilterResults results = new FilterResults();

        if (constraint.length() == 0) {
            filteredUserEntityList.addAll(originalUserEntityList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            for (final UserEntity userEntity : originalUserEntityList) {
                if (userEntity.getName().contains(filterPattern)) {
                    filteredUserEntityList.add(userEntity);
                }
            }
        }
        results.values = filteredUserEntityList;
        results.count = filteredUserEntityList.size();
        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.getFilteredList().clear();
        adapter.getFilteredList().addAll((ArrayList<UserEntity>) results.values);
        adapter.notifyDataSetChanged();
    }

    public List<UserEntity> getOriginalList(){
        return originalUserEntityList;
    }

    public void addItemToOfOriginalList(UserEntity userEntity){
        originalUserEntityList.add(0, userEntity);
        filteredUserEntityList.add(0, userEntity);

        adapter.getFilteredList().clear();
        adapter.getFilteredList().addAll(filteredUserEntityList);
        adapter.notifyDataSetChanged();
    }


    public void removeItemFromOriginalAndFilteredLisy(int position){

        for (int i = 0; i < originalUserEntityList.size(); i++) {
            if(originalUserEntityList.get(i).getName().equals(filteredUserEntityList.get(position).getName())){
                originalUserEntityList.remove(i);
            }
        }

        filteredUserEntityList.remove(position);

        adapter.getFilteredList().clear();
        adapter.getFilteredList().addAll(filteredUserEntityList);
        adapter.notifyDataSetChanged();

    }







}
