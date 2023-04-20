package com.hdv.magiccoffee.di;

import android.app.Application;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class GoogleSignInModule {

    @Singleton
    @Provides
    public GoogleSignInClient provideGoogleSignInClient(Application application) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("643880852348-4pkvdq87s1tvig24o6o1fnsjli82cc18.apps.googleusercontent.com")
                .build();
        return GoogleSignIn.getClient(application, gso);
    }
}
