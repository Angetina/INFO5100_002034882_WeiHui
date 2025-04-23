package com.example.stocktradingsimulator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {
    @FXML private TableView<StockQuote> watchlistTable;
    @FXML private TableColumn<StockQuote, String> watchlistSymbolColumn;
    @FXML private TableColumn<StockQuote, Double> watchlistPriceColumn;
    @FXML private TextField watchlistInput;

    @FXML private TableView<Holding> portfolioTable;
    @FXML private TableColumn<Holding, String> symbolColumn;
    @FXML private TableColumn<Holding, Integer> sharesColumn;
    @FXML private TableColumn<Holding, Double> avgPriceColumn;
    @FXML private TableColumn<Holding, Double> currentPriceColumn;
    @FXML private TableColumn<Holding, Double> totalValueColumn;
    @FXML private TableColumn<Holding, Double> profitLossColumn;

    @FXML private TextField buySellSymbolInput;
    @FXML private TextField sharesInput;

    @FXML private Label cashBalanceLabel;
    @FXML private Label portfolioValueLabel;
    @FXML private Label profitLossLabel;

    private final ObservableList<StockQuote> watchlistData = FXCollections.observableArrayList();
    private final ObservableList<Holding> portfolioData = FXCollections.observableArrayList();
    private final Portfolio portfolio = new Portfolio();

    @FXML
    public void initialize() {
        // Binding Watchlist TableView Fields
        watchlistSymbolColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSymbol()));
        watchlistPriceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        watchlistTable.setItems(watchlistData);

        // Binding the Portfolio TableView Fields
        symbolColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getSymbol()));
        sharesColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getShares()).asObject());
        avgPriceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getAveragePurchasePrice()).asObject());
        currentPriceColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getCurrentPrice()).asObject());
        totalValueColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getTotalValue()).asObject());
        profitLossColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().getProfitLoss()).asObject());
        portfolioTable.setItems(portfolioData);

        updateLabels();
    }
    @FXML
    private void handleAddWatchlist() {
        String symbol = watchlistInput.getText().trim();
        if (!symbol.isEmpty()) {
            StockQuoteService service = new StockQuoteService();
            double price = service.fetchQuote(symbol);
            if (price > 0) {
                watchlistData.add(new StockQuote(symbol, price));
            }
        }
    }

    @FXML
    private void handleBuy() {
        String symbol = buySellSymbolInput.getText().trim().toUpperCase();
        int shares;
        try {
            shares = Integer.parseInt(sharesInput.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Shares must be a valid number.");
            return;
        }

        if (symbol.isEmpty() || shares <= 0) {
            showAlert("Please enter a valid symbol and shares.");
            return;
        }

        double price = new StockQuoteService().fetchQuote(symbol);

        boolean success = portfolio.buy(symbol, shares, price);
        if (!success) {
            showAlert("Insufficient balance.");
            return;
        }
        updatePortfolioTable(symbol, price);
        updateLabels();
    }

    @FXML
    private void handleSell() {
        String symbol = buySellSymbolInput.getText().trim().toUpperCase();
        int shares;
        try {
            shares = Integer.parseInt(sharesInput.getText().trim());
        } catch (NumberFormatException e) {
            showAlert("Shares must be a valid number.");
            return;
        }

        double price = new StockQuoteService().fetchQuote(symbol);
        boolean success = portfolio.sell(symbol, shares, price);

        if (!success) {
            showAlert("Not enough shares to sell.");
            return;
        }

        updatePortfolioTable(symbol, price);
        updateLabels();
    }

    @FXML
    private void handleSave() {
        PortfolioManager.savePortfolio(portfolio);
    }

    @FXML
    private void handleLoad() {
        Portfolio loaded = PortfolioManager.loadPortfolio();
        this.portfolio.setHoldings(loaded.getHoldings());
        this.portfolio.setCashBalance(loaded.getCashBalance());
        this.portfolio.setWatchlistSymbols(loaded.getWatchlistSymbols());
        updateLabels();
    }

    private void updateLabels() {
        cashBalanceLabel.setText("Cash: $" + String.format("%.2f", portfolio.getCashBalance()));
        portfolioValueLabel.setText("Portfolio Value: $" + String.format("%.2f", portfolio.calculateHoldingsValue()));
        profitLossLabel.setText("Profit/Loss: $" + String.format("%.2f", portfolio.calculateTotalProfitLoss()));
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updatePortfolioTable(String symbol, double currentPrice) {

        portfolioData.clear();


        for (Holding h : portfolio.getHoldings()) {
            if (h.getSymbol().equalsIgnoreCase(symbol)) {
                h.updateCalculatedValues(currentPrice);
            }
            portfolioData.add(h);
        }
    }
}
