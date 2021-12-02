package com.wjl.site.service.intf;

import com.wjl.site.model.entity.People;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 人物服务接口
 *
 * @author: wjl
 * @date: 2021/11/21 14:23
 * @version: v1.0
 */
public interface PeopleService {

    Page<People> findAllPeoplesByPage(Pageable pageable);

    List<People> findAllPeoples();

    People findPeopleById(Long id);

    boolean addPeople(People people);

    boolean updatePeople(Long id, People people);

    boolean deletePeople(Long id);

}
