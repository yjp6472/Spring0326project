package com.my.spring0326.file.dao;

import java.util.List;

import com.my.spring0326.file.dto.FileDto;

public interface FileDao {
	public List<FileDto> getList(FileDto dto);
	public int getCount(FileDto dto);
	public void insert(FileDto dto);
	public FileDto getData(int num);
	public void delete(int num);
}
