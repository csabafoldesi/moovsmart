package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Account;
import com.progmasters.moovsmart.domain.Bookmark;
import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.dto.BookmarkData;
import com.progmasters.moovsmart.dto.PropertyListItem;
import com.progmasters.moovsmart.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final AccountService accountService;
    private final PropertyService propertyService;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository, AccountService accountService, PropertyService propertyService) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountService = accountService;
        this.propertyService = propertyService;
    }

    public void saveBookmark(BookmarkData bookmarkData) {
        Account account = accountService.findAccountByEmail(getUserDetails().getUsername());
        Property property = propertyService.findPropertyById(bookmarkData.getPropertyId());
        Optional<Bookmark> alreadySaved = bookmarkRepository.findByForeignKeys(account.getId(), property.getId());
        if (alreadySaved.isEmpty()) {
            Bookmark bookmark = new Bookmark(account, property);
            bookmarkRepository.save(bookmark);
        }
    }

    public boolean isSaved(BookmarkData bookmarkData) {
        Account account = accountService.findAccountByEmail(getUserDetails().getUsername());
        Property property = propertyService.findPropertyById(bookmarkData.getPropertyId());
        Optional<Bookmark> alreadySaved = bookmarkRepository.findByForeignKeys(account.getId(), property.getId());
        return alreadySaved.isPresent();
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

    public void deleteBookmark(BookmarkData bookmarkData) {
        Account account = accountService.findAccountByEmail(getUserDetails().getUsername());
        Property property = propertyService.findPropertyById(bookmarkData.getPropertyId());
        Optional<Bookmark> alreadySaved = bookmarkRepository.findByForeignKeys(account.getId(), property.getId());
        alreadySaved.ifPresent(bookmark -> bookmarkRepository.deleteById(bookmark.getId()));
    }

    public List<PropertyListItem> getBookmarkedProperties() {
        Account account = accountService.findAccountByEmail(getUserDetails().getUsername());
        List<Long> propertyIds = bookmarkRepository.getPropertyIdByAccountId(account.getId());
        Collections.reverse(propertyIds);
        List<PropertyListItem> propertyListItems = new ArrayList<>();
        for (Long propertyId : propertyIds) {
            PropertyListItem property = propertyService.findActivePropertyById(propertyId);
            propertyListItems.add(property);
        }
        return propertyListItems;
    }
}
