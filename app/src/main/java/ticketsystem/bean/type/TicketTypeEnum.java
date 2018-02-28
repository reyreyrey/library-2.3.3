package ticketsystem.bean.type;

import java.io.Serializable;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date: 2017/9/11 16:30
 */

public enum TicketTypeEnum implements Serializable {

    Country("全国"), Area("地方"), Out("境外"), High("高频"), Follow("关注");

    private String value;

    TicketTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
