package mobileapp.leadgraph.com.leadgraph;


import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.mozilla.javascript.tools.jsc.Main;

import mobileapp.leadgraph.com.leadgraph.fragments.ChannelCompanyFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.ChannelDesignationFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.ChannelStaffFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.HomeFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.LeadAssignFragment;
import mobileapp.leadgraph.com.leadgraph.dialog.UserAskDialog;
import mobileapp.leadgraph.com.leadgraph.fragments.LeadFollowFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.LeadHistoryFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.LeadLockFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.MasterProductCategoryFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.MasterProductFragment;
import mobileapp.leadgraph.com.leadgraph.fragments.UserRightsFragment;
import mobileapp.leadgraph.com.leadgraph.utils.SPUtils;


/**
 * Main screen of application after login in
 * this screen calls from landing page where user can go without logging in
 * and book truck after last confirmation app ask for login
 *
 * @author neeraj on 22/11/18.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private NavigationView navigationView;
    TextView title, logout;
    DrawerLayout drawer;
    ImageView menuIcon;
    FrameLayout fragment_container;
    FragmentManager fragmentManager;
    TextView master, master_channel, master_product, product_category, product_master, home, channel_designation, channel_company, channel_staff;
    TextView lead, lead_lock, lead_assign, lead_follow, lead_history;
    TextView utility, utility_right;
    RelativeLayout notificatons;
    TextView noti_counter;
    FloatingActionButton btn_logout;
    LinearLayout lin_history, lin_follow, lin_lock;
    ImageView ll_img, lf_img, lh_img;
    TextView ll_txt, lf_txt, lh_txt;
    LinearLayout lin_bot;
    TextView company_name,comapny_mail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        fragmentManager = getSupportFragmentManager();
        lin_bot = findViewById(R.id.lin_bot);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        btn_logout = navigationView.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
        noti_counter = navigationView.findViewById(R.id.noti_counter);
        notificatons = findViewById(R.id.notificatons);
        notificatons.setOnClickListener(this);
        fragment_container = findViewById(R.id.fragment_container);
        menuIcon = findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(this);
        title = findViewById(R.id.title);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        master = navigationView.findViewById(R.id.master);
        home = navigationView.findViewById(R.id.home);
        home.setOnClickListener(this);
        master_product = navigationView.findViewById(R.id.master_product);
        master_channel = navigationView.findViewById(R.id.master_channel);
        product_master = navigationView.findViewById(R.id.product_master);
        product_category = navigationView.findViewById(R.id.product_category);
        product_master.setOnClickListener(this);
        product_category.setOnClickListener(this);
        master_channel.setOnClickListener(this);
        master_product.setOnClickListener(this);
        master.setOnClickListener(this);
        master.setTag("UNEXPANDED");
        master_product.setTag("UNEXPANDED");
        master_channel.setTag("UNEXPANDED");
        lead = navigationView.findViewById(R.id.lead);
        lead.setOnClickListener(this);
        lead.setTag("UNEXPANDED");
        lead_lock = navigationView.findViewById(R.id.lead_lock);
        lead_lock.setOnClickListener(this);
        channel_designation = navigationView.findViewById(R.id.channel_designation);
        channel_designation.setOnClickListener(this);
        channel_company = navigationView.findViewById(R.id.channel_company);
        channel_company.setOnClickListener(this);
        channel_staff = navigationView.findViewById(R.id.channel_staff);
        channel_staff.setOnClickListener(this);
        lead_assign = navigationView.findViewById(R.id.lead_assign);
        lead_assign.setOnClickListener(this);
        lead_follow = navigationView.findViewById(R.id.lead_follow);
        lead_follow.setOnClickListener(this);
        lead_history = navigationView.findViewById(R.id.lead_history);
        lead_history.setOnClickListener(this);
        utility_right = navigationView.findViewById(R.id.utility_right);
        utility_right.setOnClickListener(this);
        utility = navigationView.findViewById(R.id.utility);
        utility.setTag("UNEXPANDED");
        utility.setOnClickListener(this);
        lin_history = findViewById(R.id.lin_history);
        lin_follow = findViewById(R.id.lin_follow);
        lin_lock = findViewById(R.id.lin_lock);
        lin_lock.setOnClickListener(this);
        lin_follow.setOnClickListener(this);
        lin_history.setOnClickListener(this);
        ll_img = findViewById(R.id.ll_img);
        ll_txt = findViewById(R.id.ll_txt);
        lf_img = findViewById(R.id.lf_img);
        lf_txt = findViewById(R.id.lf_txt);
        lh_img = findViewById(R.id.lh_img);
        lh_txt = findViewById(R.id.lh_txt);
        comapny_mail=findViewById(R.id.comapny_mail);
        company_name=findViewById(R.id.company_name);
        company_name.setText(SPUtils.getString(this,SPUtils.COMPANY_NAME));
        addFragment(new HomeFragment());
    }

    /**
     * set title of header
     *
     * @param text
     */
    public void setTitle(String text) {
        title.setText(text);
    }

    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    public void openDrawer() {
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menuIcon:
                openDrawer();
                break;
            case R.id.master:
                if (master.getTag().equals("UNEXPANDED")) {
                    master.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_master, 0, R.mipmap.ic_drop_down, 0);
                    master_channel.setVisibility(View.VISIBLE);
                    master_product.setVisibility(View.VISIBLE);
                    master.setTag("EXPANDED");
                } else {
                    master.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_master, 0, R.mipmap.ic_drop_right, 0);
                    master_product.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_right, 0);
                    master_channel.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_right, 0);
                    master_channel.setVisibility(View.GONE);
                    master_product.setVisibility(View.GONE);
                    product_master.setVisibility(View.GONE);
                    product_category.setVisibility(View.GONE);
                    channel_staff.setVisibility(View.GONE);
                    channel_company.setVisibility(View.GONE);
                    channel_designation.setVisibility(View.GONE);
                    master.setTag("UNEXPANDED");
                    master_product.setTag("UNEXPANDED");
                    master_channel.setTag("UNEXPANDED");
                }

                break;
            case R.id.master_product:
                if (master_product.getTag().equals("UNEXPANDED")) {
                    master_product.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_down, 0);
                    product_master.setVisibility(View.VISIBLE);
                    product_category.setVisibility(View.VISIBLE);
                    master_product.setTag("EXPANDED");
                } else {
                    master_product.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_right, 0);
                    product_master.setVisibility(View.GONE);
                    product_category.setVisibility(View.GONE);
                    master_product.setTag("UNEXPANDED");
                }
                break;
            case R.id.master_channel:
                if (master_channel.getTag().equals("UNEXPANDED")) {
                    master_channel.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_down, 0);
                    channel_staff.setVisibility(View.VISIBLE);
                    channel_company.setVisibility(View.VISIBLE);
                    channel_designation.setVisibility(View.VISIBLE);
                    master_channel.setTag("EXPANDED");
                } else {
                    master_channel.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_dot, 0, R.mipmap.ic_drop_right, 0);
                    channel_staff.setVisibility(View.GONE);
                    channel_company.setVisibility(View.GONE);
                    channel_designation.setVisibility(View.GONE);
                    master_channel.setTag("UNEXPANDED");
                }
                break;
            case R.id.home:
                for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
                    fragmentManager.popBackStackImmediate();
                }
                closeDrawer();
                break;
            case R.id.lead:
                if (lead.getTag().equals("UNEXPANDED")) {
                    lead.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_lead, 0, R.mipmap.ic_drop_down, 0);
                    lead_lock.setVisibility(View.VISIBLE);
                    lead_history.setVisibility(View.VISIBLE);
                    lead_follow.setVisibility(View.VISIBLE);
                    lead_assign.setVisibility(View.VISIBLE);
                    lead.setTag("EXPANDED");

                } else {
                    lead.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_lead, 0, R.mipmap.ic_drop_right, 0);
                    lead_lock.setVisibility(View.GONE);
                    lead_history.setVisibility(View.GONE);
                    lead_follow.setVisibility(View.GONE);
                    lead_assign.setVisibility(View.GONE);
                    lead.setTag("UNEXPANDED");
                }
                break;
            case R.id.lead_lock:
                closeDrawer();
                setSelected(false, false, false, false, false, true, false, false, false, false);
                replaceFragment(new LeadLockFragment(), "LeadLock");
                break;
            case R.id.utility:
                if (utility.getTag().equals("UNEXPANDED")) {
                    utility.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_utility, 0, R.mipmap.ic_drop_down, 0);
                    utility_right.setVisibility(View.VISIBLE);
                    utility.setTag("EXPANDED");
                } else {
                    utility.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_menu_utility, 0, R.mipmap.ic_drop_right, 0);
                    utility_right.setVisibility(View.GONE);
                    utility.setTag("UNEXPANDED");
                }
                break;
            case R.id.notificatons:
                //call notification activity here
                break;
            case R.id.product_category:
                closeDrawer();
                setSelected(true, false, false, false, false, false, false, false, false, false);
                replaceFragment(new MasterProductCategoryFragment(), "MasterCategory");
                break;
            case R.id.product_master:
                closeDrawer();
                setSelected(false, true, false, false, false, false, false, false, false, false);
                replaceFragment(new MasterProductFragment(), "MASTER PRODUCT");
                break;
            case R.id.channel_staff:
                closeDrawer();
                setSelected(false, false, true, false, false, false, false, false, false, false);
                replaceFragment(new ChannelStaffFragment(), "CHANNEL STAFF");
                break;
            case R.id.channel_company:
                closeDrawer();
                setSelected(false, false, false, true, false, false, false, false, false, false);
                replaceFragment(new ChannelCompanyFragment(), "CHANNELCOMPANY");
                break;
            case R.id.channel_designation:
                closeDrawer();
                setSelected(false, false, false, false, true, false, false, false, false, false);
                replaceFragment(new ChannelDesignationFragment(), "CHANNELDESIGNATION");
                break;
            case R.id.btn_logout:
                closeDrawer();
                UserAskDialog userAskDialog = new UserAskDialog(this, "Are you sure you want to logout?");
                userAskDialog.setAskDialogListener(new UserAskDialog.AskDialogListener() {
                    @Override
                    public void onYes() {
                        // call logout
                        SPUtils.setLogin(false, MainActivity.this);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onNo() {
                        //stay on screen
                    }
                });
                userAskDialog.show();
                break;
            case R.id.utility_right:
                closeDrawer();
                setSelected(false, false, false, false, false, false, false, false, false, true);
                replaceFragment(new UserRightsFragment(), "USERRIGHTS");
                break;
            case R.id.lin_lock:
                setBottomNavigation(true, false, false);
                replaceFragment(new LeadLockFragment(), "LEADLOCK");
                break;
            case R.id.lin_follow:
                setBottomNavigation(false, true, false);
                replaceFragment(new LeadFollowFragment(), "LEADFOLLOW");
                break;
            case R.id.lin_history:
                setBottomNavigation(false, false, true);
                replaceFragment(new LeadHistoryFragment(), "LEADHISTORY");
                break;
            case R.id.lead_assign:
                closeDrawer();
                replaceFragment(new LeadAssignFragment(), "LEADASSIGN");
                break;
            case R.id.lead_follow:
                closeDrawer();
                replaceFragment(new LeadFollowFragment(), "LEADFOLLOW");
                break;
            case R.id.lead_history:
                closeDrawer();
                replaceFragment(new LeadHistoryFragment(), "LEADHISTORY");

                break;
        }
        animateCLicks(view);
    }

    public void setBottomNavigation(boolean leadLock, boolean leadFollow, boolean leadHistory) {
        if (leadLock) {
            ll_img.setColorFilter(ContextCompat.getColor(this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            lf_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lh_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            ll_txt.setTextColor(getResources().getColor(R.color.black));
            lf_txt.setTextColor(getResources().getColor(R.color.white_color));
            lh_txt.setTextColor(getResources().getColor(R.color.white_color));

        } else if (leadFollow) {
            ll_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lf_img.setColorFilter(ContextCompat.getColor(this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            lh_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            ll_txt.setTextColor(getResources().getColor(R.color.white_color));
            lf_txt.setTextColor(getResources().getColor(R.color.black));
            lh_txt.setTextColor(getResources().getColor(R.color.white_color));
        } else if (leadHistory) {
            ll_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lf_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lh_img.setColorFilter(ContextCompat.getColor(this, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);
            ll_txt.setTextColor(getResources().getColor(R.color.white_color));
            lf_txt.setTextColor(getResources().getColor(R.color.white_color));
            lh_txt.setTextColor(getResources().getColor(R.color.black));
        } else {
            ll_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lf_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            lh_img.setColorFilter(ContextCompat.getColor(this, R.color.white_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            ll_txt.setTextColor(getResources().getColor(R.color.white_color));
            lf_txt.setTextColor(getResources().getColor(R.color.white_color));
            lh_txt.setTextColor(getResources().getColor(R.color.white_color));
        }

    }

    /**
     * adding home fragment not in backstack
     * all other fragments are replaced on container
     *
     * @param fragment
     */
    public void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    /**
     * replace fragment on container
     * and add them to backstack
     *
     * @param fragment
     * @param tag
     */
    public void replaceFragment(Fragment fragment, String tag) {
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); ++i) {
            fragmentManager.popBackStackImmediate();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(tag);

    }

    /**
     * hard ware back button pressed
     * check if all fragments removed then ask user to exit confirmation
     */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateCLicks(View view) {
        ViewAnimationUtils.createCircularReveal(view,
                view.getWidth(),
                view.getHeight(),
                0,
                view.getHeight() * 2).start();
    }

    public void didTapButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        view.startAnimation(myAnim);
    }

    public void setSelected(boolean pc, boolean pm, boolean cs, boolean cc, boolean cd, boolean ll, boolean la
            , boolean lf, boolean lh, boolean ur) {
        product_category.setSelected(pc);
        product_master.setSelected(pm);
        channel_staff.setSelected(cs);
        channel_company.setSelected(cc);
        channel_designation.setSelected(cd);
        lead_lock.setSelected(ll);
        lead_assign.setSelected(la);
        lead_follow.setSelected(lf);
        lead_history.setSelected(lh);
        utility_right.setSelected(ur);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
//        if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_YES) {
//            lin_bot.setVisibility(View.VISIBLE);
//        } else if (newConfig.keyboardHidden == Configuration.KEYBOARDHIDDEN_NO) {
//            lin_bot.setVisibility(View.GONE);
//        }
        super.onConfigurationChanged(newConfig);
    }
}
/**
 * flow of class is here
 * step1-->setting layout-->initialize views-->set click listeners->
 * step2-->we set navigation drawer custom view
 * step3-->in fragment container we add a home fragment by deffault and not set it in backstack
 **/