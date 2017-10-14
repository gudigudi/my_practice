package com.gudigudigudi.mdtemplate.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.activity.NewsContentActivity;
import com.gudigudigudi.mdtemplate.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsTitleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsTitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTitleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private boolean isTwoPane;


    private OnFragmentInteractionListener mListener;

    public NewsTitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsTitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsTitleFragment newInstance(String param1, String param2) {
        NewsTitleFragment fragment = new NewsTitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);

        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            News news = new News();
            news.setTitle("This is news titles" + i);
            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
            newsList.add(news);
        }

        return newsList;
    }

    private String getRandomLengthContent(String s) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(s);
        }
        return builder.toString();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * NewsAdapter: recyclerview adapter for News.
     */
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> newsList;

        public NewsAdapter(List<News> newsList) {
            this.newsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup group, int i) {
//            return null;

            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.news_item, group, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = newsList.get(holder.getAdapterPosition());
                    if (isTwoPane) { // pad mobile
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {// phone mode
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int i) {
            News news = newsList.get(i);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }


        /**
         * viewholder of News
         */
        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText = itemView.findViewById(R.id.news_title);
            }
        }
    }

}
