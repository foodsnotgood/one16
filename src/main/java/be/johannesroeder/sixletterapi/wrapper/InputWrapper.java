package be.johannesroeder.sixletterapi.wrapper;

import lombok.Getter;

@Getter
public record InputWrapper<T>(T input) {
}
