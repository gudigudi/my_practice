package com.gudigudigudi.mdtemplate.view.viewPager;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class LoopPagerAdapterWrapper extends PagerAdapter {

    @Getter
    @lombok.NonNull
    private final PagerAdapter mAdapter;
    private SparseArray<ToDestroy> mToDestroy = new SparseArray<ToDestroy>();

    private static final boolean DEFAULT_BOUNDARY_CASHING = true;
    private static final boolean DEFAULT_BOUNDARY_LOOPING = true;

    @Setter
    private boolean boundaryCaching = DEFAULT_BOUNDARY_CASHING;
    @Setter
    private boolean boundaryLooping = DEFAULT_BOUNDARY_LOOPING;

    @Override
    public void notifyDataSetChanged() {
        mToDestroy = new SparseArray<>();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count = getRealCount();
        return boundaryLooping ? count + 2 : count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = (mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter) ? position
                : toRealPosition(position);

        if (boundaryCaching) {
            ToDestroy toDestroy = mToDestroy.get(position);
            if (toDestroy != null) {
                mToDestroy.remove(position);
                return toDestroy.object;
            }
        }
        return mAdapter.instantiateItem(container, realPosition);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int realFirst = getRealFirstPosition();
        int realLast = getRealLastPosition();
        int realPosition = (mAdapter instanceof FragmentPagerAdapter
                || mAdapter instanceof FragmentStatePagerAdapter) ? position
                : toRealPosition(position);

        if (boundaryCaching && (position == realFirst || position == realLast)) {
            mToDestroy.put(position, new ToDestroy(container, realPosition, object));
        } else {
            mAdapter.destroyItem(container, realPosition, object);
        }
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        mAdapter.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public void restoreState(Parcelable bundle, ClassLoader classLoader) {
        mAdapter.restoreState(bundle, classLoader);
    }

    @Override
    public Parcelable saveState() {
        return mAdapter.saveState();
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        mAdapter.startUpdate(container);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        mAdapter.setPrimaryItem(container, position, object);
    }

    public int toRealPosition(int position) {
        int realPosition = position;
        int realCount = getRealCount();
        if (realCount == 0) {
            return 0;
        }
        if (boundaryLooping) {
            realPosition = (position - 1) % realCount;
            if (realPosition < 0) {
                realPosition += realCount;
            }
        }
        return realPosition;
    }

    public int toInnerPosition(int position) {
        return boundaryLooping ? position + 1 : position;
    }

    private int getRealFirstPosition() {
        return boundaryLooping ? 1 : 0;
    }

    private int getRealLastPosition() {
        return getRealFirstPosition() + getRealCount() - 1;
    }

    public int getRealCount() {
        return mAdapter.getCount();
    }

    /**
     * Container class for caching the boundary views
     */
    @AllArgsConstructor
    static class ToDestroy {
        final ViewGroup container;
        final int position;
        final Object object;
    }
}
