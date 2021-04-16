package com.meetsun.meetsun.until;
 
import com.meetsun.meetsun.until.Result;
import com.meetsun.meetsun.until.ResultEnum;
import java.io.Serializable;
 
public class Result<T> implements Serializable{
	private String status = ResultEnum.STATUS_SUCCESS.getcode();
 
	private String message = ResultEnum.STATUS_SUCCESS.getmsg();
 
	private T rows;
   
	private Integer total;
   
	public Result() {}
 
	public Result(String status, String message, T rows) {
		this.status = status;
		this.message = message;
		this.rows = rows;
	}
   
	public static Result error(String message) {
		Result<Object> result = new Result();
		result.setStatus(ResultEnum.STATUS_FAILURE.getcode());
		result.setMessage(message);
		result.setRows(null);
		return result;
	}
	
	public static Result error(String code, String message) {
		Result<Object> result = new Result();
		result.setStatus(code);
		result.setMessage(message);
		result.setRows(null);
		return result;
	}
   
	public static Result success(Integer total, Object rows) {
		Result<Object> result = new Result();
		result.setTotal(total);
		result.setRows(rows);
		return result;
   }
   
	public static Result success(Object rows) {
		Result<Object> result = new Result();
     	result.setRows(rows);
     	return result;
	}
 
	public String getStatus() { return this.status; }
 
	public void setStatus(String status) { this.status = status; }
 
	public String getMessage() { return this.message; }
 
	public void setMessage(String message) { this.message = message; }
   
	public T getRows() { return this.rows; }
 
	public Integer getTotal() { return this.total; }
   
	public void setTotal(Integer total) { this.total = total; }
 
	public void setRows(T rows) { this.rows = rows; }
 }
