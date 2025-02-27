package me.wcy.music.service;

import android.app.Application;
import android.content.Intent;
import android.os.Build;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.KeyEvent;

import me.wcy.music.application.AppCache;
import me.wcy.music.model.Music;
import me.wcy.music.utils.CoverLoader;

/**
 * Created by hzwangchenyan on 2017/8/8.
 */
public class MediaSessionManager {
    private static final String TAG = "MediaSessionManager";
//    private static final long MEDIA_SESSION_ACTIONS = PlaybackStateCompat.ACTION_PLAY
//            | PlaybackStateCompat.ACTION_PAUSE
//            | PlaybackStateCompat.ACTION_PLAY_PAUSE
//            | PlaybackStateCompat.ACTION_SKIP_TO_NEXT
//            | PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS
//            | PlaybackStateCompat.ACTION_STOP
//            | PlaybackStateCompat.ACTION_SEEK_TO;

//    private PlayService playService;
    private Application app;
    private MediaSessionCompat mediaSession;

    public static MediaSessionManager get() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static MediaSessionManager instance = new MediaSessionManager();
    }

    private MediaSessionManager() {
    }

//    public void init(PlayService playService) {
//        this.playService = playService;
//        setupMediaSession();
//    }

    public void initApp(Application app) {
        this.app = app;
        setupMediaSessionWithApp();
    }

//    private void setupMediaSession() {
//        mediaSession = new MediaSessionCompat(playService, TAG);
//        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS | MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
//        mediaSession.setCallback(callback);
//        mediaSession.setActive(true);
//    }

    private void setupMediaSessionWithApp() {
        mediaSession = new MediaSessionCompat(app, TAG);
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS | MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
        mediaSession.setCallback(callback);
        mediaSession.setActive(true);
    }

    public void updatePlaybackState() {
//        int state = (AudioPlayer.get().isPlaying() || AudioPlayer.get().isPreparing()) ? PlaybackStateCompat.STATE_PLAYING : PlaybackStateCompat.STATE_PAUSED;
//        mediaSession.setPlaybackState(
//                new PlaybackStateCompat.Builder()
//                        .setActions(MEDIA_SESSION_ACTIONS)
//                        .setState(state, AudioPlayer.get().getAudioPosition(), 1)
//                        .build());
    }

    public void updateMetaData(Music music) {
//        if (music == null) {
//            mediaSession.setMetadata(null);
//            return;
//        }
//
//        MediaMetadataCompat.Builder metaData = new MediaMetadataCompat.Builder()
//                .putString(MediaMetadataCompat.METADATA_KEY_TITLE, music.getTitle())
//                .putString(MediaMetadataCompat.METADATA_KEY_ARTIST, music.getArtist())
//                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM, music.getAlbum())
//                .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, music.getArtist())
//                .putLong(MediaMetadataCompat.METADATA_KEY_DURATION, music.getDuration())
//                .putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, CoverLoader.get().loadThumb(music));
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            metaData.putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, AppCache.get().getLocalMusicList().size());
//        }
//
//        mediaSession.setMetadata(metaData.build());
    }

    private MediaSessionCompat.Callback callback = new MediaSessionCompat.Callback() {
        @Override
        public void onPlay() {
            Log.v(TAG, "get Media Button >> onPlay");
            AudioPlayer.get().playPause();
        }

        @Override
        public void onPause() {
            Log.v(TAG, "get Media Button >> onPause");
            AudioPlayer.get().playPause();
        }

        @Override
        public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
            Log.v(TAG, mediaButtonEvent.getAction());
            Log.v(TAG, mediaButtonEvent.getParcelableExtra(Intent.EXTRA_KEY_EVENT).toString());
            return super.onMediaButtonEvent(mediaButtonEvent);
        }

        @Override
        public void onSkipToNext() {
            Log.v(TAG, "get Media Button >> onSkipToNext");
            AudioPlayer.get().next();
        }

        @Override
        public void onSkipToPrevious() {
            Log.v(TAG, "get Media Button >> onSkipToPrevious");
            AudioPlayer.get().prev();
        }

        @Override
        public void onStop() {
            Log.v(TAG, "get Media Button >> onStop");
            AudioPlayer.get().stopPlayer();
        }

        @Override
        public void onSeekTo(long pos) {
            Log.v(TAG, "get Media Button >> onSeekTo");
            AudioPlayer.get().seekTo((int) pos);
        }
    };
}
