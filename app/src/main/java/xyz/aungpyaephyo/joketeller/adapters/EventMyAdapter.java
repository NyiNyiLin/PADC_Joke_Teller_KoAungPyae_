package xyz.aungpyaephyo.joketeller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.aungpyaephyo.joketeller.JokeTellerApp;
import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.data.vos.EventVO;
import xyz.aungpyaephyo.joketeller.views.holders.EventViewMyHolder;

/**
 * Created by IN-3442 on 02-Jul-16.
 */
public class EventMyAdapter extends RecyclerView.Adapter<EventViewMyHolder>{

    private List<EventVO> mEventList;

    public EventMyAdapter(List<EventVO> eventVOList) {
        mEventList = eventVOList;
    }

    @Override
    public EventViewMyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(JokeTellerApp.getContext());
        View view = inflater.inflate(R.layout.view_item_event, parent, false);
        EventViewMyHolder eventViewMyHolder = new EventViewMyHolder(view);
        return eventViewMyHolder;
    }

    @Override
    public void onBindViewHolder(EventViewMyHolder holder, int position) {
        EventVO eventVO =mEventList.get(position);

    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}
