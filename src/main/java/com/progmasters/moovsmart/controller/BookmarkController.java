package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.BookmarkData;
import com.progmasters.moovsmart.dto.PropertyListItem;
import com.progmasters.moovsmart.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping
    public ResponseEntity<Void> createBookmark(@RequestBody BookmarkData bookmarkData) {
        bookmarkService.saveBookmark(bookmarkData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Boolean> isBookmarked(BookmarkData bookmarkData) {
        return new ResponseEntity<>(bookmarkService.isSaved(bookmarkData), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookmark(BookmarkData bookmarkData) {
        bookmarkService.deleteBookmark(bookmarkData);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/myBookmarks")
    public ResponseEntity<List<PropertyListItem>> getMyBookmarks() {
        return new ResponseEntity<>(bookmarkService.getBookmarkedProperties(), HttpStatus.OK);
    }
}
