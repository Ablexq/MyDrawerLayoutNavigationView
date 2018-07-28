


```
    compile 'com.android.support:design:25.3.1'
```




DrawerLayout
===

```
android.support.v4.widget.DrawerLayout

public class DrawerLayout extends ViewGroup implements DrawerLayoutImpl {
```


使用时直接将DrawerLayout作为根布局，然后其内部第一个View为内容区域，第二个View为左侧菜单，第三个View为右侧侧滑菜单，
当然第三个是可选的。主内容区的布局代码要放在侧滑菜单布局的前面,这样可以帮助DrawerLayout判断谁是侧滑菜单，谁是主内容区；
第一个View也即主界面的宽高应当设置为match_parent。
第二、三个View需要设置android:layout_gravity="left"，和android:layout_gravity="right"且一般高度设置为match_parent，
宽度为固定值，即侧滑菜单的宽度。




NavigationView
====
其实就是drawerlayout拉出来后的那些菜单。


```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout
    android:id="@+id/drawer_layout"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_drawer_layout__one"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_drawer_layout__one"
        app:menu="@menu/activity_drawer_layout__one_drawer"/>

</android.support.v4.widget.DrawerLayout>

```
可以看到我们的最外层是DrawerLayout，包含了两个内容：include为显示内容区域，NavigationView为侧边抽屉栏。

NavigationView有两个app属性，分别为app:headerLayout和app:menu，
headerLayout用于显示头部的布局（可选），
menu用于建立MenuItem选项的菜单。

参考：[DrawerLayout和NavigationView使用详解](https://www.jianshu.com/p/d2b1689a23bf)




FloatingActionButton
===

参考：

[FloatingActionButton属性、用法，以及解析并解决sdk25以上只隐藏不显示的问题](https://blog.csdn.net/chen_xi_hao/article/details/74347023)

[FloatingActionButton基本使用及踩坑记录](https://blog.csdn.net/gaolh89/article/details/79759404)

[关于解决自定义FloatingActionButton滑动行为（Behavior）只隐藏不出现的问题](https://blog.csdn.net/victor_fang/article/details/72305820)


FAB的精髓在于与CoordinatorLayout协调者布局中的使用。


```
public class FloatingActionButton extends VisibilityAwareImageButton {

class VisibilityAwareImageButton extends ImageButton {

public class ImageButton extends ImageView {

public class ImageView extends View {

```


```
<android.support.design.widget.FloatingActionButton
        android:id="@+id/contact_fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|right"
        android:layout_margin="10dp"
        android:src="@mipmap/ic_launcher"
        app:backgroundTint="@color/gray"
        app:backgroundTintMode="multiply"
        app:borderWidth="0dp"
        app:elevation="@dimen/activity_horizontal_margin"
        app:fabSize="auto"
        app:pressedTranslationZ="@dimen/activity_horizontal_margin"
        app:rippleColor="@color/gray"
        app:useCompatPadding="true" />
```



【阴影】

app:elevation 
默认状态下阴影大小。阴影的高度，elevation是Android 5.0中引入的新属性，
设置该属性使控件有一个阴影，感觉该控件像是“浮”起来一样，这样达到3D效果。对应的方法：setCompatElevation(float)

app:pressedTranslationZ 
按钮按下去的状态下的阴影大小

【背景色】

app:backgroundTint 
FAB按钮的背景颜色，不设置，默认使用theme中colorAccent的颜色

app:rippleColor 
设置点击时的背景颜色

【图片】

android:src 
设置FAB的图标，Google建议符合Design设计的该图标大小为24dp。

【位置】
app:layout_anchor：和app:layout_anchorGravity属性一起搭配使用

app:layout_anchor 
设置FAB的锚点，即以哪个控件为参照点设置位置。

app:layout_anchorGravity 
设置FAB相对锚点的位置，值有 bottom、center、right、left、top等。

【其他】

android:scaleType=”center”
会把中间的小图放到最大。

app:backgroundTintMode 
按钮背景颜色的模式，在设置screen的时候就跟其他模式有点区别，区别在颜色变了，其他不变，具体不详，可忽略

app:borderWidth 
设置FAB的边框宽度，
这个一般设置为0dp，该属性如果不设置0dp，那么在4.1的sdk上FAB会显示为正方形，而且在5.0以后的sdk没有阴影效果。所以设置为borderWidth=”0dp”


app:fabSize 
设置大小，该属性有两个值，分别为normal和mini，对应的大小分别为56dp和40dp
FAB的大小，为normal时，大小为：56 * 56dp ，为mini时，大小为： 40 * 40 dp。

app:useCompatPadding 
是否使用兼容的填充大小

app:rippleColor="#e7d16b"：
点击FAB时，形成的波纹颜色。

android:layout_marginBottom :
由于FAB 支持库仍然存在一些 bug，在 Kitkat 和 Lollipop 中

API21+ 的版本统一定义底部与右边缘空白为 16dp，Lollipop 以下版本统一设置为 0dp.

解决办法：

values/dimens.xml
```
<dimen name="fab_margin_right">0dp</dimen>
<dimen name="fab_margin_bottom">0dp</dimen>
```
values-v21/dimens.xml
```
<dimen name="fab_margin_right">16dp</dimen>
<dimen name="fab_margin_bottom">16dp</dimen>
```

布局文件的 FAB 中，也设置相应的值：
```
<android.support.design.widget.FloatingActionButton
    ...
    ...
    android:layout_marginBottom="@dimen/fab_margin_bottom"
    android:layout_marginRight="@dimen/fab_margin_right"/>
```

特别注意: 
1.要想让FAB显示点击后的颜色和阴影变化效果，必须设置onClick事件。 

2.上述的app:layout_anchor,父类布局使用FrameLayout是没有效果的,需要使用加强版的FrameLayout即CoordinatorLayout。
参考锚点不能以父类为参考,要不然会报错:
java.lang.IllegalStateException: View can not be anchored to the the parent CoordinatorLayout。

正确的xml代码如下(TextView可以换成其他View):
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/cl"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    >

    <TextView
        android:id="@+id/tv"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:clickable="true"
        android:src="@drawable/fab_up"
        app:backgroundTint="#FFFFFF"
        app:borderWidth="0dp"
        app:elevation="5dp"
        app:fabSize="mini"
        app:layout_anchor="@id/tv"
        app:layout_anchorGravity="bottom|right|end"
        app:pressedTranslationZ="10dp"
        app:rippleColor="#a6a6a6"
        />
</android.support.design.widget.CoordinatorLayout>
```
当然,FAB的位置,在这里您不使用CoordinatorLayout,而是直接使用FrameLayout,
同时通过android:layout_gravity=”bottom|right”属性确定FAB的位,在这里是可以的。但,不建议这样使用。



坑：
===

CoordinatorLayout类中：
```
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
            int dxUnconsumed, int dyUnconsumed) {
        final int childCount = getChildCount();
        boolean accepted = false;

        for (int i = 0; i < childCount; i++) {
            final View view = getChildAt(i);
            if (view.getVisibility() == GONE) {
                // If the child is GONE, skip...
                continue;
            }

            final LayoutParams lp = (LayoutParams) view.getLayoutParams();
            if (!lp.isNestedScrollAccepted()) {
                continue;
            }

            final Behavior viewBehavior = lp.getBehavior();
            if (viewBehavior != null) {
                viewBehavior.onNestedScroll(this, view, target, dxConsumed, dyConsumed,
                        dxUnconsumed, dyUnconsumed);
                accepted = true;
            }
        }

        if (accepted) {
            onChildViewsChanged(EVENT_NESTED_SCROLL);
        }
    }
```
不要用hide()方法，也不要使用setVisibility(GONE)，可以使用setVisibility(INVISIBLE)，或者将其移除屏幕显示范围来达到隐藏的效果

参考：

[FloatingActionButton属性、用法，以及解析并解决sdk25以上只隐藏不显示的问题](https://blog.csdn.net/chen_xi_hao/article/details/74347023)

[zhaochenpu/CoordinatorLayoutDemo](https://github.com/zhaochenpu/CoordinatorLayoutDemo/blob/master/app/src/main/java/com/example/zcp/coordinatorlayoutdemo/behavior/MyFabBehavior.java)


