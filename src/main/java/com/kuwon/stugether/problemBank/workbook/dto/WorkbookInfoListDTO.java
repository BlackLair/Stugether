package com.kuwon.stugether.problemBank.workbook.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkbookInfoListDTO {
	private List<WorkbookInfo> workbookInfoList;
	private int totalCount;
}
