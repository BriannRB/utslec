// Generated by view binder compiler. Do not edit!
package com.example.uts_lec.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.uts_lec.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignupBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView backButton;

  @NonNull
  public final EditText birthdayInput;

  @NonNull
  public final EditText emailInput;

  @NonNull
  public final EditText nameInput;

  @NonNull
  public final EditText passwordInput;

  @NonNull
  public final Button signUpButton;

  @NonNull
  public final TextView signUpTitle;

  private ActivitySignupBinding(@NonNull LinearLayout rootView, @NonNull ImageView backButton,
      @NonNull EditText birthdayInput, @NonNull EditText emailInput, @NonNull EditText nameInput,
      @NonNull EditText passwordInput, @NonNull Button signUpButton,
      @NonNull TextView signUpTitle) {
    this.rootView = rootView;
    this.backButton = backButton;
    this.birthdayInput = birthdayInput;
    this.emailInput = emailInput;
    this.nameInput = nameInput;
    this.passwordInput = passwordInput;
    this.signUpButton = signUpButton;
    this.signUpTitle = signUpTitle;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignupBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signup, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignupBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButton;
      ImageView backButton = ViewBindings.findChildViewById(rootView, id);
      if (backButton == null) {
        break missingId;
      }

      id = R.id.birthdayInput;
      EditText birthdayInput = ViewBindings.findChildViewById(rootView, id);
      if (birthdayInput == null) {
        break missingId;
      }

      id = R.id.emailInput;
      EditText emailInput = ViewBindings.findChildViewById(rootView, id);
      if (emailInput == null) {
        break missingId;
      }

      id = R.id.nameInput;
      EditText nameInput = ViewBindings.findChildViewById(rootView, id);
      if (nameInput == null) {
        break missingId;
      }

      id = R.id.passwordInput;
      EditText passwordInput = ViewBindings.findChildViewById(rootView, id);
      if (passwordInput == null) {
        break missingId;
      }

      id = R.id.signUpButton;
      Button signUpButton = ViewBindings.findChildViewById(rootView, id);
      if (signUpButton == null) {
        break missingId;
      }

      id = R.id.signUpTitle;
      TextView signUpTitle = ViewBindings.findChildViewById(rootView, id);
      if (signUpTitle == null) {
        break missingId;
      }

      return new ActivitySignupBinding((LinearLayout) rootView, backButton, birthdayInput,
          emailInput, nameInput, passwordInput, signUpButton, signUpTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
