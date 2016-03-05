package com.example.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private PostAdapter postAdapter;

    private Post[] posts;

    List<Post> postList;

   private ListView mListView;

//    Post[] posts = {
//            new Post("Cupcake", "1.5", R.drawable.cupcake),
//            new Post("Donut", "1.6", R.drawable.donut),
//            new Post("Eclair", "2.0-2.1", R.drawable.eclair),
//            new Post("Froyo", "2.2-2.2.3", R.drawable.froyo),
//            new Post("GingerBread", "2.3-2.3.7", R.drawable.gingerbread),
//            new Post("Honeycomb", "3.0-3.2.6", R.drawable.honeycomb),
//            new Post("Ice Cream Sandwich", "4.0-4.0.4", R.drawable.icecream),
//            new Post("Jelly Bean", "4.1-4.3.1", R.drawable.jellybean),
//            new Post("KitKat", "4.4-4.4.4", R.drawable.kitkat),
//            new Post("Lollipop", "5.0-5.1.1", R.drawable.lollipop)
//    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        fetchPost();
//        postAdapter = new PostAdapter(getActivity(), Arrays.asList(posts));
//
//        // Get a reference to the ListView, and attach this adapter to it.
//        ListView listView = (ListView) rootView.findViewById(R.id.listview_post);
//        listView.setAdapter(postAdapter);

        return rootView;
    }




    private void fetchPost() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("http://jsonplaceholder.typicode.com/")
                .build();

        FdbApiService git = restAdapter.create(FdbApiService.class);

        git.getPost(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> flowers, Response response) {
                postList = flowers;
                 mListView = (ListView) getView().findViewById(R.id.listview_post);
                postAdapter = new PostAdapter(getActivity(), postList);
                mListView.setAdapter(postAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }


}