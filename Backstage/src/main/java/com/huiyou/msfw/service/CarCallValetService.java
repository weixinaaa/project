package com.huiyou.msfw.service;

import com.huiyou.msfw.model.CarCallValet;
import com.huiyou.msfw.model.CarCallValetExample;

import java.util.List;

public interface CarCallValetService {
    List<CarCallValet> getCarCallValetByExample(CarCallValetExample carCallValetExample);
}
