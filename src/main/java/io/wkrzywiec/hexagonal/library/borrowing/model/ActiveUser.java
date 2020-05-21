package io.wkrzywiec.hexagonal.library.borrowing.model;

import io.wkrzywiec.hexagonal.library.borrowing.model.exception.TooManyBooksAssignedToUserException;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class ActiveUser {

    private final Long id;
    private final List<ReservedBook> reservedBooks;

    public ActiveUser(Long id, List<ReservedBook> reservedBooks) {
        this.id = id;
        this.reservedBooks = reservedBooks;
    }

    public ReservedBook reserve(AvailableBook availableBook){
        if (reservedBooks.size() < 3){
            ReservedBook reservedBook = new ReservedBook(availableBook.getIdAsLong(), id);
            reservedBooks.add(reservedBook);
            return reservedBook;
        } else {
            throw new TooManyBooksAssignedToUserException(id);
        }
    }

    public Long getIdAsLong(){
        return id;
    }

    public List<ReservedBook> getReservedBookList(){
        return reservedBooks;
    }
}
