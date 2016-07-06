package xyz.aungpyaephyo.joketeller.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.adapters.EventAdapter;
import xyz.aungpyaephyo.joketeller.data.models.EventModel;
import xyz.aungpyaephyo.joketeller.data.vos.EventVO;

/**
 * Created by aung on 6/25/16.
 */
public class EventFragment extends Fragment {

    private EventAdapter mEventAdapter;
    private ControllerEventItem mEventItemController;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mEventItemController = (ControllerEventItem) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventAdapter = new EventAdapter(EventModel.getInstance().getEventList(), mEventItemController);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        RecyclerView rvEvent = (RecyclerView) view.findViewById(R.id.rv_events);
        rvEvent.setAdapter(mEventAdapter);
        rvEvent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return view;
    }

    public interface ControllerEventItem {
        void onTapEvent(EventVO event, ImageView ivStockPhoto);
    }
}