package com.wjl.site.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.wjl.site.component.QueryComponent;
import com.wjl.site.model.entity.People;
import com.wjl.site.model.entity.QPeople;
import com.wjl.site.repository.PeopleRepository;
import com.wjl.site.service.intf.PeopleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 人物服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/21 14:24
 * @version: v1.0
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private QueryComponent queryComponent;

    @Autowired
    private PeopleRepository peopleRepository;

    QPeople qPeople = QPeople.people;

    @Override
    public Page<People> findAllPeoplesByPage(Pageable pageable) {
        QueryResults<Tuple> queryResults = queryComponent.queryFactory()
                .select(
                        qPeople.id,
                        qPeople.name,
                        qPeople.picture,
                        qPeople.mention,
                        qPeople.introduction
                )
                .from(qPeople)
                .orderBy(qPeople.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Tuple> tuples = queryResults.getResults();
        List<People> peoples = new ArrayList<>();
        for (Tuple tuple : tuples) {
            People people = new People();
            people.setId(tuple.get(qPeople.id));
            people.setName(tuple.get(qPeople.name));
            people.setPicture(tuple.get(qPeople.picture));
            people.setMention(tuple.get(qPeople.mention));
            people.setIntroduction(tuple.get(qPeople.introduction));
            peoples.add(people);
        }
        return new PageImpl<>(peoples, pageable, queryResults.getTotal());
    }

    @Override
    public List<People> findAllPeoples() {
        return peopleRepository.findAll();
    }

    @Override
    public People findPeopleById(Long id) {
        return peopleRepository.findPeopleById(id);
    }

    @Override
    public boolean addPeople(People people) {
        if (!Objects.isNull(people)) {
            peopleRepository.save(people);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePeople(Long id, People people) {
        if (!Objects.isNull(people)) {
            People people1 = findPeopleById(id);
            if (!Objects.isNull(people1)) {
                BeanUtils.copyProperties(people, people1, "id");
                peopleRepository.save(people1);
                peopleRepository.flush();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deletePeople(Long id) {
        if (!Objects.isNull(findPeopleById(id))) {
            peopleRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
