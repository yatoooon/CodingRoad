package com.yatoooon.recyclerview_core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserViewHolder> {

    private List<User> userList;

    private Context context;

    public void setData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public UserRVAdapter(Context context) {
        this.context = context;
    }


    //列表item/广告的impression统计用这个 onBindViewHolder可能不准确 因为有可能有缓存
    @Override
    public void onViewAttachedToWindow(@NonNull UserViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_user, viewGroup, false);
        UserViewHolder holder = new UserViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() { //在创建的时候设置监听器
            @Override
            public void onClick(View v) {

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        userViewHolder.bindUser(userList.get(position));
        //userViewHolder.itemView.setOnClickListener();  这种设置点击监听器不好  不应该在bind的时候设置
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    /*
       viewholder是什么? 不要重复写findviewbyid
       1. View holder 和 item view 是什么关系？  一对一的
       2. View holder 解决的是什么问题?  不重复写findviewById
       3 .View holder 和 ListView item view 的复用有什么关系？  没有关系 就算没有viewholder  itemview也可以复用
        */
    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAge;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
        }

        void bindUser(User user) {
            tvName.setText(user.getName());
            tvAge.setText(user.getAge());
        }
    }
}
