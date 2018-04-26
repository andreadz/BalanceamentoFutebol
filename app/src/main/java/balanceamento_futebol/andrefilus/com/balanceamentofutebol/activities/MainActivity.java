package balanceamento_futebol.andrefilus.com.balanceamentofutebol.activities;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import balanceamento_futebol.andrefilus.com.balanceamentofutebol.R;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.commands.FragmentCommand;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.databinding.ActivityMainBinding;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.fragments.HomeFragment;
import balanceamento_futebol.andrefilus.com.balanceamentofutebol.fragments.SelectPlayersFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentActionListener, SelectPlayersFragment.OnSelectPlayersFragmentActionListener  {

    private ActivityMainBinding mActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setTitle("teste de toolbar");
        if (savedInstanceState == null)
            FragmentCommand.replaceFragmentToContainer(this, "Teste 2", R.id.fragmentContainer, HomeFragment.newInstance(null), FragmentCommand.ANIMATION_FADE);


        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                int backStackEntryCount = fragmentManager.getBackStackEntryCount();
                if (backStackEntryCount > 0) {
                    FragmentManager supportFragmentManager = getSupportFragmentManager();
                    FragmentManager.BackStackEntry backStackEntry = supportFragmentManager.getBackStackEntryAt(backStackEntryCount - 1);
                    if (TextUtils.isEmpty(backStackEntry.getBreadCrumbTitle())) {
                        setTitle("tstest");
                    } else setTitle(backStackEntry.getBreadCrumbTitle().toString());
                }
            }
        });
    }

    @Override
    public void onHomeFragmentSelectCourt(@NonNull String name, @NonNull String typeCourt) {
        FragmentCommand.addFragmentToContainer(this, "Teste",
                R.id.fragmentContainer, SelectPlayersFragment.newInstance(name, typeCourt), FragmentCommand.ANIMATION_SLIDE);
    }

    @Override
    public void onGenerateTeams() {

    }
}
