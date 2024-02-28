package edu.xhu.lhms.module.common.vo;

/**
 * @Description Result
 * @Author JiangHu
 * @Date 2023/6/12 10:43
 */
public class Result<T> {

	private int status;
	private String message;
	private T data;

	public Result() {
	}

	public Result(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Result(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static Result ok() {
		return new Result(ResultStatus.SUCCESS.code, "Success.");
	}

	public static <T> Result ok(T data) {
		return new Result(ResultStatus.SUCCESS.code, "Success.", data);
	}

	public static Result faild() {
		return new Result(ResultStatus.FAILD.code, "Faild.");
	}

	public static Result faild(String message) {
		return new Result(ResultStatus.FAILD.code, message);
	}

	/**
	 * ResultStatus
	 */
	public enum ResultStatus {
		SUCCESS(200),
		FAILD(500)
		;
		public int code;

		ResultStatus(int code) {
			this.code = code;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
