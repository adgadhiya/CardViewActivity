package com.example.unknown.cardviewactivity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter(this,albumList);

        RecyclerView.LayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpTopx(3),false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.sankalp2).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(
                new AppBarLayout.OnOffsetChangedListener() {
                    boolean isShow = false;
                    int scrollRange = -1;
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                        if(scrollRange == -1){
                            scrollRange = appBarLayout.getTotalScrollRange();
                        }

                        if(scrollRange + verticalOffset == 0){
                            collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                            isShow = true;
                        } else  {
                            collapsingToolbarLayout.setTitle(" ");
                            isShow = false;
                        }
                    }
                }
        );
    }

    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.mcd,
                R.drawable.uspizza,
                R.drawable.dominos,
                R.drawable.neo,
                R.drawable.sankalp,
                R.drawable.taaza,
                R.mipmap.subway,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Album a = new Album("McDonald", 13, covers[0]);
        albumList.add(a);

        a = new Album("Subway", 8, covers[1]);
        albumList.add(a);

        a = new Album("Domino's Pizza", 11, covers[2]);
        albumList.add(a);

        a = new Album("Neo Politan Pizzza", 12, covers[3]);
        albumList.add(a);

        a = new Album("Sankalp", 14, covers[4]);
        albumList.add(a);

        a = new Album("Tazza Pizza", 1, covers[5]);
        albumList.add(a);

        a = new Album("U.S.Pizza", 11, covers[6]);
        albumList.add(a);

        a = new Album("Legend", 14, covers[7]);
        albumList.add(a);

        a = new Album("Hello", 11, covers[8]);
        albumList.add(a);

        a = new Album("Greatest Hits", 17, covers[9]);
        albumList.add(a);

        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration{

        private int spanCount;
        private int spacing;
        private boolean includeedge;

        public GridSpacingItemDecoration(int spanCount,int spacing,boolean includeedge){
            this.includeedge = includeedge;
            this.spacing=spacing;
            this.spanCount = spanCount;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeedge){
                outRect.left = spacing - column * spacing/spanCount;
                outRect.right=(column + 1) * spacing / spanCount;

                if(position < spanCount){
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;

//                Toast.makeText(MainActivity.this, String.valueOf(outRect.left) +" " + String.valueOf(outRect.right)+" " + String.valueOf(outRect.top)+ " "+ String.valueOf(outRect.bottom) , Toast.LENGTH_SHORT).show();

            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right=spacing - (column + 1) * spacing /spanCount;

                if(position >= spanCount){
                    outRect.top = spacing;
                }
            }
        }
    }


    private int dpTopx(int dp){
        Resources r = getResources();
         int a=  Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
        Toast.makeText(MainActivity.this,String.valueOf(a),Toast.LENGTH_LONG).show();
        return a;
    }


}
