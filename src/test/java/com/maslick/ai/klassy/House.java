package com.maslick.ai.klassy;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private double houseSize;
    private double lotSize;
    private int bedrooms;
    private int granite;
    private double bathroom;
}