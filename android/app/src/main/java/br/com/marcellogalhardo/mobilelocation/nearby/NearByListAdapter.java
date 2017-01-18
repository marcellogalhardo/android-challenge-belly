package br.com.marcellogalhardo.mobilelocation.nearby;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.marcellogalhardo.mobilelocation.R;
import br.com.marcellogalhardo.mobilelocation.data.Business;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NearByListAdapter extends RecyclerView.Adapter<NearByListAdapter.ViewHolder> {

    private final List<Business> businesses;

    private OnBusinessClickedListener onBusinessClickedListener;

    public NearByListAdapter() {
        businesses = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_near_by, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Context context = holder.view.getContext();
        Business business = businesses.get(position);

        Picasso.with(holder.thumb.getContext())
                .load(business.getThumb())
                .placeholder(R.drawable.ic_business)
                .into(holder.thumb);

        holder.name.setText(business.getName());
        String distance = context.getString(R.string.miles_away, business.getDistance());
        holder.distance.setText(distance);

        if (business.isClosed()) {
            holder.status.setText(R.string.closed);
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.font_closed));
        } else {
            holder.status.setText(R.string.open);
            holder.status.setTextColor(ContextCompat.getColor(context, R.color.font_open));
        }

        if (business.getCategories() != null && business.getCategories().size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(business.getCategories().get(0).get(0));

            for (int i = 1; i < business.getCategories().size(); i++) {
                builder.append(", ");
                builder.append(business.getCategories().get(i).get(0));
            }

            holder.category.setVisibility(View.VISIBLE);
            holder.category.setText(builder.toString());
        } else {
            holder.category.setVisibility(View.INVISIBLE);
        }

        if (onBusinessClickedListener != null) {
            holder.view.setOnClickListener(v ->
                    onBusinessClickedListener.onBusinessClicked(position, business));
        } else {
            holder.view.setOnClickListener(null);
        }
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    public void refresh(List<Business> businesses) {
        this.businesses.clear();
        this.businesses.addAll(businesses);
        notifyDataSetChanged();
    }

    public void clear() {
        int num = businesses.size();
        businesses.clear();
        notifyItemRangeRemoved(0, num);
    }

    public void setOnBusinessClickedListener(OnBusinessClickedListener onBusinessClickedListener) {
        this.onBusinessClickedListener = onBusinessClickedListener;
    }

    public interface OnBusinessClickedListener {
        void onBusinessClicked(int position, Business business);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;

        @BindView(R.id.thumb)
        ImageView thumb;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.distance)
        TextView distance;

        @BindView(R.id.category)
        TextView category;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.chevron)
        ImageView chevron;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
            DrawableCompat.setAutoMirrored(chevron.getDrawable(), true);
        }
    }

}