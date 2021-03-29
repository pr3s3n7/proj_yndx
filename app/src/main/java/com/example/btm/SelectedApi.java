package com.example.btm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SelectedApi {

    @GET("/api/v1/quote?symbol=YNDX&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getYndxPriceData();
    @GET("/api/v1/quote?symbol=AAPL&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getAaplPriceData();
    @GET("/api/v1/quote?symbol=GOOGL&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getGooglPriceData();
    @GET("/api/v1/quote?symbol=AMZN&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getAmznPriceData();
    @GET("/api/v1/quote?symbol=BAC&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getBacPriceData();
    @GET("/api/v1/quote?symbol=MSFT&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getMsftPriceData();
    @GET("/api/v1/quote?symbol=TSLA&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getTslaPriceData();
    @GET("/api/v1/quote?symbol=MA&token=c10flon48v6oijd8e0j0")
    Call<PriceModel> getMaPriceData();

    @GET("/api/v1/company-news?symbol=YNDX&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getYndxNewsData();
    @GET("/api/v1/company-news?symbol=AAPL&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getAaplNewsData();
    @GET("/api/v1/company-news?symbol=GOOGL&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getGooglNewsData();
    @GET("/api/v1/company-news?symbol=AMZN&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getAmznNewsData();
    @GET("/api/v1/company-news?symbol=BAC&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getBacNewsData();
    @GET("/api/v1/company-news?symbol=MSFT&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getMsftNewsData();
    @GET("/api/v1/company-news?symbol=TSLA&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getTslaNewsData();
    @GET("/api/v1/company-news?symbol=MA&token=c10flon48v6oijd8e0j0")
    Call<List<NewsModel>> getMaNewsData();

    @GET("/api/v1/stock/candle?symbol=YNDX&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getYndxGraphDayData();
    @GET("/api/v1/stock/candle?symbol=AAPL&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAaplGraphDayData();
    @GET("/api/v1/stock/candle?symbol=GOOGL&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getGooglGraphDayData();
    @GET("/api/v1/stock/candle?symbol=AMZN&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAmznGraphDayData();
    @GET("/api/v1/stock/candle?symbol=BAC&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getBacGraphDayData();
    @GET("/api/v1/stock/candle?symbol=MSFT&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMsftGraphDayData();
    @GET("/api/v1/stock/candle?symbol=TSLA&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getTslaGraphDayData();
    @GET("/api/v1/stock/candle?symbol=MA&resolution=1&from=1616488590&to=1616574990&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMaGraphDayData();

    @GET("/api/v1/stock/candle?symbol=YNDX&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getYndxGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=AAPL&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAaplGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=GOOGL&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getGooglGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=AMZN&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAmznGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=BAC&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getBacGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=MSFT&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMsftGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=TSLA&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getTslaGraphMonthData();
    @GET("/api/v1/stock/candle?symbol=MA&resolution=15&from=1614272251&to=1616691451&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMaGraphMonthData();

    @GET("/api/v1/stock/candle?symbol=YNDX&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getYndxGraphYearData();
    @GET("/api/v1/stock/candle?symbol=AAPL&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAaplGraphYearData();
    @GET("/api/v1/stock/candle?symbol=GOOGL&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getGooglGraphYearData();
    @GET("/api/v1/stock/candle?symbol=AMZN&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getAmznGraphYearData();
    @GET("/api/v1/stock/candle?symbol=BAC&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getBacGraphYearData();
    @GET("/api/v1/stock/candle?symbol=MSFT&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMsftGraphYearData();
    @GET("/api/v1/stock/candle?symbol=TSLA&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getTslaGraphYearData();
    @GET("/api/v1/stock/candle?symbol=MA&resolution=1&from=1614124800&to=1615302599&token=c10flon48v6oijd8e0j0")
    Call<GraphModel> getMaGraphYearData();
}
