package xyz.aungpyaephyo.joketeller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.aungpyaephyo.joketeller.JokeTellerApp;
import xyz.aungpyaephyo.joketeller.R;
import xyz.aungpyaephyo.joketeller.data.vos.EventVO;
import xyz.aungpyaephyo.joketeller.fragments.EventFragment;
import xyz.aungpyaephyo.joketeller.views.holders.EventViewHolder;

/**
 * Created by aung on 6/26/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private LayoutInflater inflater;
    private List<EventVO> eventList;
    private EventFragment.ControllerEventItem mEventItemController;

    public EventAdapter(List<EventVO> eventList, EventFragment.ControllerEventItem eventItemController) {
        inflater = LayoutInflater.from(JokeTellerApp.getContext());
        this.eventList = eventList;
        mEventItemController = eventItemController;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_item_event, parent, false);
        final EventViewHolder eventVH = new EventViewHolder(view, mEventItemController);
        return eventVH;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        holder.setData(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}