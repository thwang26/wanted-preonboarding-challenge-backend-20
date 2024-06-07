package com.wantedmarket.Item.controller;

import com.wantedmarket.Item.dto.ItemDto;
import com.wantedmarket.Item.facade.ItemFacade;
import com.wantedmarket.Item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemFacade itemFacade;

    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ItemDto.Response> addItem(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ItemDto.Request itemDto
    ) {
        return ResponseEntity.ok(itemFacade.addItem(userDetails.getUsername(), itemDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ItemDto.Response>> getItems(
    ) {
        return ResponseEntity.ok(itemFacade.getItems());
    }

    @GetMapping("/detail/{itemId}")
    public ResponseEntity<ItemDto.Response> getItemDetail(
            @PathVariable(value = "itemId") Long id
    ) {
        return ResponseEntity.ok(itemFacade.getItemDetail(id));
    }

    @GetMapping("/purchased")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ItemDto.Response>> getPurchasedItem(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(itemFacade.getPurchasedItem(userDetails.getUsername()));
    }

    @GetMapping("/reserved")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ItemDto.Response>> getReservedItem(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(itemFacade.getReservedItem(userDetails.getUsername()));
    }
}
