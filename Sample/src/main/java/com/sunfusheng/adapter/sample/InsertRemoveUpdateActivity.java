package com.sunfusheng.adapter.sample;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.sunfusheng.adapter.GroupLinearLayoutManager;
import com.sunfusheng.adapter.sample.adapter.HeaderFooterGroupAdapter;
import com.sunfusheng.adapter.sample.util.GroupData;
import com.sunfusheng.adapter.sample.util.Utils;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class InsertRemoveUpdateActivity extends BaseActivity {

    private HeaderFooterGroupAdapter mAdapter;

    private String[] new_insert_group = {"插入的新组", "a", "b", "c"};
    private String[][] new_insert_groups = {
            {"插入的新组1", "A", "B", "C"},
            {"插入的新组2", "a", "b", "c", "d"}
    };
    private String new_insert_item = "插入的新组项";
    private String[] new_insert_items = {"插入的新组项1", "插入的新组项2", "插入的新组项3"};

    private String[] new_update_group = {"更新的新组", "A", "B", "C", "D", "E"};
    private String[][] new_update_groups = {
            {"更新的新组1", "A", "B", "C"},
            {"更新的新组2", "a", "b", "c"}
    };
    private String new_update_item = "更新的新组项";
    private String[] new_update_items = {"更新的新组项1", "更新的新组项2", "更新的新组项3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler_view);

        initActionBar(R.string.insert_remove_update, true);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GroupLinearLayoutManager(this));
        mAdapter = new HeaderFooterGroupAdapter(this, GroupData.items);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener((adapter, holder, groupPosition, childPosition) -> {
            String header = mAdapter.getItem(groupPosition, 0);
            String child = mAdapter.getItem(groupPosition, childPosition);
            Utils.toast(this, header + " : " + child +
                    "\ngroupPosition: " + groupPosition +
                    "\nchildPosition: " + childPosition);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_insert_remove_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean isSuccess = true;
        switch (item.getItemId()) {
            case R.id.insert_group:
                isSuccess = mAdapter.insertGroup(1, new_insert_group);
                break;
            case R.id.insert_groups:
                isSuccess = mAdapter.insertGroups(1, new_insert_groups);
                break;
            case R.id.insert_item:
                isSuccess = mAdapter.insertItem(1, 1, new_insert_item);
                break;
            case R.id.insert_items:
                isSuccess = mAdapter.insertItems(1, 1, new_insert_items);
                break;
            case R.id.remove_group:
                isSuccess = mAdapter.removeGroup(0);
                break;
            case R.id.remove_groups:
                isSuccess = mAdapter.removeGroups(1, 2);
                break;
            case R.id.remove_item:
                isSuccess = mAdapter.removeItem(1, 1);
                break;
            case R.id.remove_items:
                isSuccess = mAdapter.removeItems(1, 1, 2);
                break;
            case R.id.update_group:
                isSuccess = mAdapter.updateGroup(1, new_update_group);
                break;
            case R.id.update_groups:
                isSuccess = mAdapter.updateGroups(1, new_update_groups);
                break;
            case R.id.update_item:
                isSuccess = mAdapter.updateItem(1, 1, new_update_item);
                break;
            case R.id.update_items:
                isSuccess = mAdapter.updateItems(1, 0, new_update_items);
                break;
        }

        if (!isSuccess) {
            Utils.toast(this, "操作失败，请检查Data或Position");
        }
        return super.onOptionsItemSelected(item);
    }
}