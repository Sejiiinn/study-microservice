package org.msa.item.controller;

import org.msa.item.dto.ItemDTO;
import org.msa.item.dto.ResponseDTO;
import org.msa.item.exception.ApiException;
import org.msa.item.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "v1/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseDTO> add(@Valid @RequestBody ItemDTO itemDTO) throws Exception {
		ResponseDTO.ResponseDTOBuilder responseDTOBuilder = ResponseDTO.builder();
		itemService.insertItem(itemDTO);

		try {
			Integer.parseInt("test");
		} catch (Exception e) {
			throw new ApiException("test 에러");
		}

		log.debug("request add item id = {}", itemDTO.getId());

		responseDTOBuilder.code("200").message("success");
		return ResponseEntity.ok(responseDTOBuilder.build());
	}
}
