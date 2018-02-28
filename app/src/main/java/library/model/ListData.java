package library.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListData<T> extends BaseInfo implements Serializable{

	@SerializedName("total")
	public int size;

	@SerializedName("curPage")
	public int curPage;

	@SerializedName("result")
	public List<T> list;

}
