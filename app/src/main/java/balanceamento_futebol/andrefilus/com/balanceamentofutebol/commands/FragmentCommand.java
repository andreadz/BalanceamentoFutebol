package balanceamento_futebol.andrefilus.com.balanceamentofutebol.commands;

import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import balanceamento_futebol.andrefilus.com.balanceamentofutebol.R;

/**
 * Created by André Filus on 25/04/2018.
 */

public abstract class FragmentCommand {
    public static final int ANIMATION_FADE = 1;
    public static final int ANIMATION_SLIDE = 2;

    /**
     * Obtain the {@link FragmentManager} from {@link Fragment} and do the needed job to add a new fragment
     * as children of another fragment. Simple as that!
     * @param fragmentsFather a {@link Fragment} that MUST be the father of all the fragments
     * @param viewContainerId a {@link IdRes} to the view that will be used as the container to the newly add fragment
     * @param fragmentToAdd a {@link Fragment} to be added to the father fragment
     * @param animation {@link FragmentCommand#ANIMATION_FADE} or {@link FragmentCommand#ANIMATION_SLIDE}
     */
    public static void addFragmentToContainer(@NonNull Fragment fragmentsFather, @IdRes int viewContainerId, @NonNull Fragment fragmentToAdd, int animation) {
        reallyAddOrReplaceFragmentToContainer(fragmentsFather.getChildFragmentManager(), "", viewContainerId, fragmentToAdd, true, animation);
    }

    /**
     * Obtain the {@link FragmentManager} from {@link AppCompatActivity} and do the needed job to add a new fragment
     * as children of the activity. Simple as that!
     * @param fragmentsFather a {@link AppCompatActivity} that MUST be the father of all the fragments
     * @param title a {@link String} to be set as title of activity
     * @param viewContainerId a {@link IdRes} to the view that will be used as the container to the newly add fragment
     * @param fragmentToAdd a {@link Fragment} to be added to the father activity
     * @param animation {@link FragmentCommand#ANIMATION_FADE} or {@link FragmentCommand#ANIMATION_SLIDE}
     */
    public static void addFragmentToContainer(@NonNull final AppCompatActivity fragmentsFather, @Nullable final String title, @IdRes final int viewContainerId, @NonNull final Fragment fragmentToAdd, final int animation) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                reallyAddOrReplaceFragmentToContainer(fragmentsFather.getSupportFragmentManager(), title, viewContainerId, fragmentToAdd, true, animation);
            }
        });
    }

    /**
     * Obtain the {@link FragmentManager} from {@link Fragment} and do the needed job to replace a new fragment
     * as children of another fragment. Simple as that!
     * @param fragmentsFather a {@link Fragment} that MUST be the father of all the fragments
     * @param title a {@link String} to be set as title of activity
     * @param viewContainerId a {@link IdRes} to the view that will be used as the container to the newly add fragment
     * @param fragmentToAdd a {@link Fragment} to be replaced to the father fragment
     * @param animation {@link FragmentCommand#ANIMATION_FADE} or {@link FragmentCommand#ANIMATION_SLIDE}
     */
    public static void replaceFragmentToContainer(@NonNull Fragment fragmentsFather, @Nullable String title, @IdRes int viewContainerId, @NonNull Fragment fragmentToAdd, int animation) {
        reallyAddOrReplaceFragmentToContainer(fragmentsFather.getChildFragmentManager(), title, viewContainerId, fragmentToAdd, false, animation);
    }

    /**
     * Obtain the {@link FragmentManager} from {@link AppCompatActivity} and do the needed job to add a replace fragment
     * as children of the activity. Simple as that!
     * @param fragmentsFather a {@link AppCompatActivity} that MUST be the father of all the fragments
     * @param title a {@link String} to be set as title of activity
     * @param viewContainerId a {@link IdRes} to the view that will be used as the container to the newly add fragment
     * @param fragmentToAdd a {@link Fragment} to be replaced to the father activity
     * @param animation {@link FragmentCommand#ANIMATION_FADE} or {@link FragmentCommand#ANIMATION_SLIDE}
     */
    public static void replaceFragmentToContainer(@NonNull AppCompatActivity fragmentsFather, @Nullable String title, @IdRes int viewContainerId, @NonNull Fragment fragmentToAdd, int animation) {
        reallyAddOrReplaceFragmentToContainer(fragmentsFather.getSupportFragmentManager(), title, viewContainerId, fragmentToAdd, false, animation);
    }

    /**
     * Removes all the fragments that are on the stack of {@link FragmentManager} WITHOUT animations
     * @param fragmentManager a {@link FragmentManager} to have his fragments removed
     */
    public static void clearFragmentManagerStack(@NonNull FragmentManager fragmentManager) {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private static void reallyAddOrReplaceFragmentToContainer(@NonNull FragmentManager fragmentManager, @Nullable String title, @IdRes int viewContainerId, @NonNull Fragment fragmentToAdd, boolean isAdding, int animation) {
        // Check with animation we want to use
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAdding) {
            // If adding check for animation and always add it to the backstack
            if (animation == ANIMATION_FADE)
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
            else
                fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right);
            fragmentTransaction.add(viewContainerId, fragmentToAdd, fragmentToAdd.getClass().getName());
            fragmentTransaction.setBreadCrumbTitle(title);
            fragmentTransaction.addToBackStack(fragmentToAdd.getClass().getName());
        } else
            fragmentTransaction.replace(viewContainerId, fragmentToAdd);
        fragmentTransaction.commit();
    }

    private static boolean isFragmentInBackstack(final FragmentManager fragmentManager, final String fragmentTagName) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }
}
