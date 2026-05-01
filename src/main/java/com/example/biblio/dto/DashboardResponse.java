package com.example.biblio.dto;

public class DashboardResponse {

    private long totalUsers;
    private long totalBooks;
    private long totalEmprunts;
    private long pendingEmprunts;
    private long returnedBooks;
    private long overdueBooks;
    private long totalBibliothecaires;
    private long availableBooks;
    private long lowStockBooks;

    public DashboardResponse(
            long totalUsers,
            long totalBooks,
            long totalEmprunts,
            long pendingEmprunts,
            long returnedBooks,
            long overdueBooks,
            long totalBibliothecaires,
            long availableBooks,
            long lowStockBooks
    ) {
        this.totalUsers = totalUsers;
        this.totalBooks = totalBooks;
        this.totalEmprunts = totalEmprunts;
        this.pendingEmprunts = pendingEmprunts;
        this.returnedBooks = returnedBooks;
        this.overdueBooks = overdueBooks;
        this.totalBibliothecaires = totalBibliothecaires;
        this.availableBooks = availableBooks;
        this.lowStockBooks = lowStockBooks;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getTotalEmprunts() {
        return totalEmprunts;
    }

    public long getPendingEmprunts() {
        return pendingEmprunts;
    }

    public long getReturnedBooks() {
        return returnedBooks;
    }

    public long getOverdueBooks() {
        return overdueBooks;
    }

    public long getTotalBibliothecaires() {
        return totalBibliothecaires;
    }

    public long getAvailableBooks() {
        return availableBooks;
    }

    public long getLowStockBooks() {
        return lowStockBooks;
    }
}