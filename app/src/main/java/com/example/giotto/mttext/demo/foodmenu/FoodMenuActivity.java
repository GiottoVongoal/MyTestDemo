package com.example.giotto.mttext.demo.foodmenu;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.giotto.mttext.demo.R;
import com.example.giotto.mttext.demo.foodmenu.adapter.ShopAdapter;
import com.example.giotto.mttext.demo.foodmenu.adapter.TestSectionedAdapter;
import com.example.giotto.mttext.demo.foodmenu.assistant.ShopToDetailListener;
import com.example.giotto.mttext.demo.foodmenu.assistant.onCallBackListener;
import com.example.giotto.mttext.demo.foodmenu.mode.ProductType;
import com.example.giotto.mttext.demo.foodmenu.mode.ShopProduct;
import com.example.giotto.mttext.demo.foodmenu.view.PinnedHeaderListView;
import com.example.giotto.mttext.demo.utils.MyUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GiottoVongoal杨丽亚.
 * @PackageName com.example.giotto.mttext.demo.foodmenu
 * @date 2017-02-27 10:33
 * @description 食物菜单
 */
public class FoodMenuActivity extends Activity implements View.OnClickListener, onCallBackListener, ShopToDetailListener {
    private boolean isScroll = true;
    private ListView mainlist;
    private PinnedHeaderListView morelist;
    private TestSectionedAdapter sectionedAdapter;
    /**
     * 保存购物车对象到List
     * 考虑保存购物车缓存
     */
    private List<ShopProduct> productList;
    //购物车价格
    private TextView shoppingPrise;
    //购物车件数
    private TextView shoppingNum;
    //去结算
    private TextView settlement;
    //购物车View
    private FrameLayout cardLayout;

    private LinearLayout cardShopLayout;
    //背景View
    private View bg_layout;
    //购物车Logo
    private ImageView shopping_cart;
    // 动画时间
    private int AnimationDuration = 500;
    // 正在执行的动画数量
    private int number = 0;
    // 是否完成清理
    private boolean isClean = false;
    private FrameLayout animation_viewGroup;
    //购物车为空的默认文字
    private TextView defaultText;

    private List<String> strings;

    //父布局
    private RelativeLayout parentLayout;
    //暂无更多数据
    private TextView noData;

    //分类列表
    private List<ProductType> productCategorizes;

    private List<ShopProduct> shopProductsAll;

    private ListView shoppingListView;

    private ShopAdapter shopAdapter;

    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 用来清除动画后留下的垃圾
                    try {
                        animation_viewGroup.removeAllViews();
                    } catch (Exception e) {

                    }
                    isClean = false;

                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu_layout);
        initView();
    }

    public List<ProductType> getData() {
        productCategorizes = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            ProductType productCategorize = new ProductType();
            productCategorize.setType("分类信息" + i);
            shopProductsAll = new ArrayList<>();
            for (int j = 1; j < 6; j++) {
                ShopProduct product = new ShopProduct();
                product.setId(154788 + i + j);
                product.setGoods("衬衫" + i);
                product.setPrice(18 + "");
                shopProductsAll.add(product);
            }
            productCategorize.setProduct(shopProductsAll);
            productCategorizes.add(productCategorize);
        }
        return productCategorizes;
    }

    private void initView() {
        getData();
        animation_viewGroup = createAnimLayout();
        noData = (TextView) findViewById(R.id.noData);
        parentLayout = (RelativeLayout) findViewById(R.id.parentLayout);
        shoppingPrise = (TextView) findViewById(R.id.shoppingPrise);
        shoppingNum = (TextView) findViewById(R.id.shoppingNum);
        settlement = (TextView) findViewById(R.id.settlement);
        mainlist = (ListView) findViewById(R.id.classify_mainlist);
        morelist = (PinnedHeaderListView) findViewById(R.id.classify_morelist);
        shopping_cart = (ImageView) findViewById(R.id.shopping_cart);
        defaultText = (TextView) findViewById(R.id.defaultText);
//        shoppingList = (LinearLayout) getView().findViewById(R.id.shoppingList);
        shoppingListView = (ListView) findViewById(R.id.shopproductListView);
        cardLayout = (FrameLayout) findViewById(R.id.cardLayout);
        cardShopLayout = (LinearLayout) findViewById(R.id.cardShopLayout);
        bg_layout = findViewById(R.id.bg_layout);
        initData();
    }

    public void initData() {
        productList = new ArrayList<>();
        strings = new ArrayList<>();
        sectionedAdapter = new TestSectionedAdapter(FoodMenuActivity.this, productCategorizes);

        sectionedAdapter.SetOnSetHolderClickListener(new TestSectionedAdapter.HolderClickListener() {

            @Override
            public void onHolderClick(Drawable drawable, int[] start_location) {
                doAnim(drawable, start_location);
            }

        });

        for (ProductType type : productCategorizes) {
            strings.add(type.getType());
        }
        morelist.setAdapter(sectionedAdapter);
        sectionedAdapter.setCallBackListener(this);
        mainlist.setAdapter(new ArrayAdapter<String>(FoodMenuActivity.this,
                R.layout.categorize_item, strings));

        shopAdapter = new ShopAdapter(FoodMenuActivity.this, productList);
        shoppingListView.setAdapter(shopAdapter);
        shopAdapter.setShopToDetailListener(this);
        //左边ListView的点击事件切换右边列表数据
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view,
                                    int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < mainlist.getChildCount(); i++) {
                    if (i == position) {
                        mainlist.getChildAt(i).setBackgroundColor(
                                Color.rgb(255, 255, 255));
                    } else {
                        mainlist.getChildAt(i).setBackgroundColor(
                                Color.TRANSPARENT);
                    }
                }

                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                morelist.setSelection(rightSection);
            }
        });
        //右边ListView滑动位置来更新左边ListView分类信息的位置切换
        morelist.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView arg0, int arg1) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < mainlist.getChildCount(); i++) {

                        if (i == sectionedAdapter
                                .getSectionForPosition(firstVisibleItem)) {
                            mainlist.getChildAt(i).setBackgroundColor(
                                    Color.rgb(255, 255, 255));
                        } else {
                            mainlist.getChildAt(i).setBackgroundColor(
                                    Color.TRANSPARENT);
                        }
                    }

                } else {
                    isScroll = true;
                }
            }
        });

        bg_layout.setOnClickListener(this);
        settlement.setOnClickListener(this);
        shopping_cart.setOnClickListener(this);
    }

    /**
     * @param product
     * @param type    Type表示添加或减少
     * @Description 回调函数更新购物车和价格显示状态
     */
    @Override
    public void updateProduct(ShopProduct product, String type) {
        if (type.equals("1")) {
            if (!productList.contains(product)) {
                productList.add(product);
            } else {
                for (ShopProduct shopProduct : productList) {
                    if (product.getId() == shopProduct.getId()) {
                        shopProduct.setNumber(shopProduct.getNumber());
                    }
                }
            }
        } else if (type.equals("2")) {
            if (productList.contains(product)) {
                if (product.getNumber() == 0) {
                    productList.remove(product);
                } else {
                    for (ShopProduct shopProduct : productList) {
                        if (product.getId() == shopProduct.getId()) {
                            shopProduct.setNumber(shopProduct.getNumber());
                        }
                    }
                }

            }
        }
        shopAdapter.notifyDataSetChanged();
        setPrise();
    }

    /**
     * @param product
     * @param type    Type表示添加或减少
     * @Description 对商品列表进行更新
     */
    @Override
    public void onUpdateDetailList(ShopProduct product, String type) {
        if (type.equals("1")) {
            for (int i = 0; i < productCategorizes.size(); i++) {
                shopProductsAll = productCategorizes.get(i).getProduct();
                for (ShopProduct shopProduct : shopProductsAll) {
                    if (product.getId() == shopProduct.getId()) {
                        shopProduct.setNumber(product.getNumber());
                    }
                }
            }
        } else if (type.equals("2")) {
            for (int i = 0; i < productCategorizes.size(); i++) {
                shopProductsAll = productCategorizes.get(i).getProduct();
                for (ShopProduct shopProduct : shopProductsAll) {
                    if (product.getId() == shopProduct.getId()) {
                        shopProduct.setNumber(product.getNumber());
                    }
                }
            }
        }
        sectionedAdapter.notifyDataSetChanged();
        setPrise();
    }

    /**
     * @param product
     * @Description 购物车商品数目为0时更新删除购物车该商品信息
     */
    @Override
    public void onRemovePriduct(ShopProduct product) {
        for (int i = 0; i < productCategorizes.size(); i++) {
            shopProductsAll = productCategorizes.get(i).getProduct();
            for (ShopProduct shopProduct : shopProductsAll) {
                if (product.getId() == shopProduct.getId()) {
                    productList.remove(product);
                    shopAdapter.notifyDataSetChanged();
                    shopProduct.setNumber(shopProduct.getNumber());
                }
            }
        }
        sectionedAdapter.notifyDataSetChanged();
        shopAdapter.notifyDataSetChanged();
        setPrise();
    }

    /**
     * @Description 更新购物车价格
     */
    public void setPrise() {
        double sum = 0;
        int shopNum = 0;
        for (ShopProduct pro : productList) {
//            sum = sum + (pro.getNumber() * Double.parseDouble(pro.getPrice()));
            sum = DoubleUtil.sum(sum, DoubleUtil.mul((double) pro.getNumber(), Double.parseDouble(pro.getPrice())));
            shopNum = shopNum + pro.getNumber();
        }
        if (shopNum > 0) {
            shoppingNum.setVisibility(View.VISIBLE);
        } else {
            shoppingNum.setVisibility(View.GONE);
        }
        if (sum > 0) {
            shoppingPrise.setVisibility(View.VISIBLE);
        } else {
            shoppingPrise.setVisibility(View.GONE);
        }
        shoppingPrise.setText("¥" + " " + (new DecimalFormat("0.00")).format(sum));
        shoppingNum.setText(String.valueOf(shopNum));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopping_cart:
                if (productList.isEmpty() || productList == null) {
                    defaultText.setVisibility(View.VISIBLE);
                } else {
                    defaultText.setVisibility(View.GONE);
                }

                if (cardLayout.getVisibility() == View.GONE) {
                    cardLayout.setVisibility(View.VISIBLE);

                    // 加载动画
                    Animation animation = AnimationUtils.loadAnimation(FoodMenuActivity.this, R.anim.push_alpha_bottom_in);
                    // 动画开始
                    cardShopLayout.setVisibility(View.VISIBLE);
                    cardShopLayout.startAnimation(animation);
                    bg_layout.setVisibility(View.VISIBLE);
                } else {
                    cardLayout.setVisibility(View.GONE);
                    bg_layout.setVisibility(View.GONE);
                    cardShopLayout.setVisibility(View.GONE);
                }
                break;

            case R.id.settlement:
                if (productList == null || productList.size() <= 0) {
                    MyUtils.showToast(FoodMenuActivity.this, "您还未点菜");
                    return;
                }
                Intent intent = new Intent(FoodMenuActivity.this, FoodResultActivity.class);
                ArrayList<ShopProduct> list = new ArrayList<ShopProduct>();
                for (int i = 0; i < productList.size(); i++) {
                    list.add(productList.get(i));
                }
                intent.putParcelableArrayListExtra("devices", list);
                startActivity(intent);
                break;

            case R.id.bg_layout:
                cardLayout.setVisibility(View.GONE);
                bg_layout.setVisibility(View.GONE);
                cardShopLayout.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private FrameLayout createAnimLayout() {
        ViewGroup rootView = (ViewGroup) FoodMenuActivity.this.getWindow().getDecorView();
        FrameLayout animLayout = new FrameLayout(FoodMenuActivity.this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;

    }

    private void doAnim(Drawable drawable, int[] start_location) {
        if (!isClean) {
            setAnim(drawable, start_location);
        } else {
            try {
                animation_viewGroup.removeAllViews();
                isClean = false;
                setAnim(drawable, start_location);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                isClean = true;
            }
        }
    }

    /**
     * @param drawable       将要加入购物车的商品
     * @param start_location 起始位置
     * @Description 动画效果设置
     */
    @SuppressLint("NewApi")
    private void setAnim(Drawable drawable, int[] start_location) {
        Animation mScaleAnimation = new ScaleAnimation(1.2f, 0.6f, 1.2f, 0.6f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        mScaleAnimation.setFillAfter(true);

        final ImageView iview = new ImageView(FoodMenuActivity.this);
        iview.setImageDrawable(drawable);
        final View view = addViewToAnimLayout(animation_viewGroup, iview,
                start_location);


        view.setAlpha(0.6f);

        int[] end_location = new int[2];
        settlement.getLocationInWindow(end_location);

        // 计算位移
        int endX = 0 - start_location[0] + 40;// 动画位移的X坐标
        int endY = end_location[1] - start_location[1];// 动画位移的y坐标
        TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0,
                0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);


        Animation mRotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
                0.3f);
        mRotateAnimation.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(mRotateAnimation);
        set.addAnimation(mScaleAnimation);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(500);// 动画的执行时间
        view.startAnimation(set);
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                number++;
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                number--;
                if (number == 0) {
                    isClean = true;
                    myHandler.sendEmptyMessage(0);
                }

                ObjectAnimator.ofFloat(shopping_cart, "translationY", 0, 4, -2, 0).setDuration(400).start();
                ObjectAnimator.ofFloat(shoppingNum, "translationY", 0, 4, -2, 0).setDuration(400).start();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }

    /**
     * @param vg       动画运行的层 这里是frameLayout
     * @param view     要运行动画的View
     * @param location 动画的起始位置
     * @return
     * @deprecated 将要执行动画的view 添加到动画层
     */
    private View addViewToAnimLayout(ViewGroup vg, View view, int[] location) {
        int x = location[0];
        int y = location[1];
        vg.addView(view);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setPadding(5, 5, 5, 5);
        view.setLayoutParams(lp);

        return view;
    }

    /**
     * @Description 内存过低时及时处理动画产生的未处理冗余
     */
    @Override
    public void onLowMemory() {
        isClean = true;
        try {
            animation_viewGroup.removeAllViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isClean = false;
        super.onLowMemory();
    }

    /**
     * @Description 返回的时候先关闭购物车蒙版
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (bg_layout.getVisibility() == View.VISIBLE) {
                bg_layout.setVisibility(View.GONE);
                cardLayout.setVisibility(View.GONE);
                cardShopLayout.setVisibility(View.GONE);
            } else {
                dialog();
            }
        }
        return false;
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FoodMenuActivity.this);
        builder.setMessage("确认退出程序吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                FoodMenuActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
