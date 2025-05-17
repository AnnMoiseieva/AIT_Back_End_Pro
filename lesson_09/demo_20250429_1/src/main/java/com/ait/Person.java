package com.ait;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Person {
    private String name;
    private int age;
    private int weight;
    private int height;
}
