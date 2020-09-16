package com.ijkalra.auspostalinfo.response;

import com.ijkalra.auspostalinfo.entity.Suburb;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuburbDetails {
    private boolean found;
    private List<Suburb> result;
}
