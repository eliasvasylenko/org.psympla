package org.psympla.language;

public interface Language<C> {
  <T> Designation<C, T> designation(Sign<T> sign);
}
