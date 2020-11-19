package com.example.myrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DailyMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<Item> mItems;
    public static final int NORMAL_WITHOUT_IMAGE = 2;
    public static final int NORMAL_WITH_IMAGE = 3;
    public static final  int Header = 4;
    public static final int Footer = 5;





    public DailyMenuAdapter(Context context, ArrayList<Item> items) {
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    public class ItemWithoutImage extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mContent;
        private TextView mTime;

        public ItemWithoutImage(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.icon);
            mContent = itemView.findViewById(R.id.content_item);
            mTime = itemView.findViewById(R.id.time_item);
        }

        public void setIcon(int image) {
            mIcon.setImageResource(image);
        }

        public void setContent(String text) {
            mContent.setText(text);
        }

        public void setTime(String text) {
            mTime.setText(text);
        }
    }

    public class ItemWithImage extends RecyclerView.ViewHolder {
        private TextView mContent;
        private TextView mTime;
        private ImageView mImage;

        public ItemWithImage(View itemView) {
            super(itemView);
            mContent = itemView.findViewById(R.id.content_item);
            mTime = itemView.findViewById(R.id.time_item);
            mImage = itemView.findViewById(R.id.image);
        }

        public void setContent(String text) {
            mContent.setText(text);
        }

        public void setTime(String text) {
            mTime.setText(text);
        }

        public void setImage(int image) {
            mImage.setTag(image);
            mImage.setImageResource(image);
        }
    }

    public class Footer extends RecyclerView.ViewHolder {
        private  View footerView;



        public void setFooterView(View footerView) {
            this.footerView = footerView;
        }
        public Footer(View itemView) {
            super(itemView);
            footerView = itemView.findViewById(R.id.viewFooter);

        }
    }
    public class Header extends RecyclerView.ViewHolder {
        private TextView headerTextView;
        public Header (View itemView) {
            super(itemView);

            headerTextView = itemView.findViewById(R.id.textView);

            }
        public void setHeaderText(String text) {
            this.headerTextView.setText(text);
        }


        }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView;
        switch (i) {
            case NORMAL_WITHOUT_IMAGE:
                mItemView = mLayoutInflater.inflate(R.layout.item_list_without_image, viewGroup, false);
                return new ItemWithoutImage(mItemView);
            case NORMAL_WITH_IMAGE:
                mItemView = mLayoutInflater.inflate(R.layout.item_list_with_image, viewGroup, false);
                return new ItemWithImage(mItemView);
            case Header:
                 mItemView = mLayoutInflater.inflate(R.layout.item_list_header, viewGroup,false );
                 return new Header(mItemView);
            case Footer:
                mItemView = mLayoutInflater.inflate(R.layout.item_list_footer, viewGroup, false);
                return  new Footer(mItemView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case NORMAL_WITHOUT_IMAGE:
                ItemWithoutImage mItemWithout = (ItemWithoutImage) viewHolder;
                mItemWithout.setContent(mItems.get(i).getContent());
                mItemWithout.setTime(mItems.get(i).getTime());
                break;
            case NORMAL_WITH_IMAGE:
                ItemWithImage mItemWith = (ItemWithImage) viewHolder;
                mItemWith.setContent(mItems.get(i).getContent());
                mItemWith.setTime(mItems.get(i).getTime());
                mItemWith.setImage(mItems.get(i).getImage());
                break;
            case Header:
                Header mHeader = (Header) viewHolder;
                mHeader.setHeaderText(mItems.get(i).getContent());
                break;
            case Footer:
                Footer mFooter=(Footer) viewHolder;
                break;




        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItems.get(position).isWithImage())
            return NORMAL_WITH_IMAGE;
        else if(mItems.get(position).isHeader()) {
            return Header;
        }
        else if (mItems.get(position).isFooter()) {
            return Footer;
        }
        else {
            return NORMAL_WITHOUT_IMAGE;
        }
    }
}
