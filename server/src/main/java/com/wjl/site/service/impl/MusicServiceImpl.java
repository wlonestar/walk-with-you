package com.wjl.site.service.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.wjl.site.component.QueryComponent;
import com.wjl.site.model.entity.Music;
import com.wjl.site.model.entity.QMusic;
import com.wjl.site.repository.MusicRepository;
import com.wjl.site.service.intf.MusicService;
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
 * 音乐服务接口实现类
 *
 * @author: wjl
 * @date: 2021/11/19 19:08
 * @version: v1.0
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private QueryComponent queryComponent;

    @Autowired
    private MusicRepository musicRepository;

    QMusic qMusic = QMusic.music;

    @Override
    public Page<Music> findAllMusicsByPage(Pageable pageable) {
        QueryResults<Tuple> queryResults = queryComponent.queryFactory()
                .select(
                        qMusic.id,
                        qMusic.name,
                        qMusic.link,
                        qMusic.background,
                        qMusic.appreciation
                )
                .from(qMusic)
                .orderBy(qMusic.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Tuple> tuples = queryResults.getResults();
        List<Music> musics = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Music music = new Music();
            music.setId(tuple.get(qMusic.id));
            music.setName(tuple.get(qMusic.name));
            music.setLink(tuple.get(qMusic.link));
            music.setBackground(tuple.get(qMusic.background));
            music.setAppreciation(tuple.get(qMusic.appreciation));
            musics.add(music);
        }
        return new PageImpl<>(musics, pageable, queryResults.getTotal());
    }

    @Override
    public List<Music> findAllMusics() {
        return musicRepository.findAll();
    }

    @Override
    public Music findMusicById(Long id) {
        return musicRepository.findMusicById(id);
    }

    @Override
    public boolean addMusic(Music music) {
        if (!Objects.isNull(music)) {
            musicRepository.save(music);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMusic(Long id, Music music) {
        if (!Objects.isNull(music)) {
            Music music1 = findMusicById(id);
            if (!Objects.isNull(music1)) {
                BeanUtils.copyProperties(music, music1, "id");
                musicRepository.save(music1);
                musicRepository.flush();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteMusic(Long id) {
        if (!Objects.isNull(findMusicById(id))) {
            musicRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
