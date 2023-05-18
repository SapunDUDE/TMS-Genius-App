package com.genius.tms_c61_genius.mapper;

import com.genius.tms_c61_genius.model.domain.Album;
import com.genius.tms_c61_genius.model.domain.Song;
import com.genius.tms_c61_genius.model.domain.Artist;
import com.genius.tms_c61_genius.model.request.SongReqDto;
import com.genius.tms_c61_genius.model.response.SongResDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongDtoMapper {
    public Song songReqToSong(SongReqDto songReqDto, List<Artist> artists, Album album){
        return Song.builder()
                .songTitle(songReqDto.getSongTitle())
                .text(songReqDto.getText())
                .duration(songReqDto.getDuration())
                .artists(artists)
                .album(album)
                .build();

    }
    public SongResDto songToSongRes(Song song){
        return  SongResDto.builder()
                .songTitle(song.getSongTitle())
                .duration(song.getDuration())
                .text(song.getText())
                .build();
    }
}
