package pe.comercio.materialsearchview.view;

import android.util.Log;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import pe.comercio.materialsearchview.model.UserEntity;
import pe.comercio.materialsearchview.view.adapter.SecondAdapter;
import pe.comercio.materialsearchview.view.adapter.UserAdapter;

/**
 * Created by Carlos Vargas on 11/25/16.
 * CarlitosDroid
 */

public class SecondFilter extends Filter {
    private List<UserEntity> originalUserEntityList;
    private List<UserEntity> filteredUserEntityList;
    private SecondAdapter adapter;

    public SecondFilter(List<UserEntity> originalUserEntityList, SecondAdapter adapter) {
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


    public void updateItemFromOriginalAndFilteredLisy(String itemName, String stringDateUpdated, int position){

        for (int i = 0; i < originalUserEntityList.size(); i++) {
            if(originalUserEntityList.get(i).getName().equals(itemName)){
                originalUserEntityList.get(i).setDateTime(stringDateUpdated);
            }
        }

        filteredUserEntityList.get(position).setDateTime(stringDateUpdated);


        Collections.sort(originalUserEntityList);
        Collections.reverse(originalUserEntityList);

//        Collections.sort(filteredUserEntityList);
//        Collections.reverse(filteredUserEntityList);
        //Collections.reverse(filteredUserEntityList);

        adapter.getFilteredList().clear();
        adapter.getFilteredList().addAll(filteredUserEntityList);
        adapter.notifyDataSetChanged();

    }
//    public void updateItemFromOriginalAndFilteredLisy(String itemName, String stringDateUpdated, int position){
//
//        for (int i = 0; i < originalUserEntityList.size(); i++) {
//            if(originalUserEntityList.get(i).getName().equals(filteredUserEntityList.get(position).getName())){
//                originalUserEntityList.get(i).setDateTime(stringDateUpdated);
//            }
//        }
//
//        filteredUserEntityList.get(position).setDateTime(stringDateUpdated);
//
//
//        Collections.sort(originalUserEntityList);
//        Collections.reverse(originalUserEntityList);
//
////        Collections.sort(filteredUserEntityList);
////        Collections.reverse(filteredUserEntityList);
//        //Collections.reverse(filteredUserEntityList);
//
//        adapter.getFilteredList().clear();
//        adapter.getFilteredList().addAll(filteredUserEntityList);
//        adapter.notifyDataSetChanged();
//
//    }

}
