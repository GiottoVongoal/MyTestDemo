package com.example.giotto.mttext.demo.linechart.mpandroidchart;

import java.util.List;

/**
 * @Author yly
 * @Data 2019/4/28 14:33
 * @Description  
 */
public class MpChartBean {
    private RspHeadBean rspHead;
    private RspBodyBean rspBody;

    public RspHeadBean getRspHead() {
        return rspHead;
    }

    public void setRspHead(RspHeadBean rspHead) {
        this.rspHead = rspHead;
    }

    public RspBodyBean getRspBody() {
        return rspBody;
    }

    public void setRspBody(RspBodyBean rspBody) {
        this.rspBody = rspBody;
    }

    public static class RspHeadBean {
        /**
         * transCode : refreshHomePage
         * retCode : 0
         * retMsg : 查询成功
         */

        private String transCode;
        private String retCode;
        private String retMsg;

        public String getTransCode() {
            return transCode;
        }

        public void setTransCode(String transCode) {
            this.transCode = transCode;
        }

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getRetMsg() {
            return retMsg;
        }

        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }
    }

    public static class RspBodyBean {
        private List<List2Bean> list2;
        private List<List1Bean> list1;

        public List<List2Bean> getList2() {
            return list2;
        }

        public void setList2(List<List2Bean> list2) {
            this.list2 = list2;
        }

        public List<List1Bean> getList1() {
            return list1;
        }

        public void setList1(List<List1Bean> list1) {
            this.list1 = list1;
        }

        public static class List2Bean {
            private String qyName;
            private String qyCode;
            private List<ListxBean> listx;

            public List2Bean() {
            }

            public List2Bean(String qyName, String qyCode, List<ListxBean> listx) {
                this.qyName = qyName;
                this.qyCode = qyCode;
                this.listx = listx;
            }

            public String getQyName() {
                return qyName;
            }

            public void setQyName(String qyName) {
                this.qyName = qyName;
            }

            public String getQyCode() {
                return qyCode;
            }

            public void setQyCode(String qyCode) {
                this.qyCode = qyCode;
            }

            public List<ListxBean> getListx() {
                return listx;
            }

            public void setListx(List<ListxBean> listx) {
                this.listx = listx;
            }

            public static class ListxBean {
                /**
                 * xqyName : 东莞一区
                 * xqyFrze : 10099990
                 * xqyCode : 0151
                 */

                private String xqyName;
                private float xqyFrze;
                private String xqyCode;

                public String getXqyName() {
                    return xqyName;
                }

                public void setXqyName(String xqyName) {
                    this.xqyName = xqyName;
                }

                public float getXqyFrze() {
                    return xqyFrze;
                }

                public void setXqyFrze(float xqyFrze) {
                    this.xqyFrze = xqyFrze;
                }

                public String getXqyCode() {
                    return xqyCode;
                }

                public void setXqyCode(String xqyCode) {
                    this.xqyCode = xqyCode;
                }
            }
        }

        public static class List1Bean {
            private float je;
            private String name;

            public float getJe() {
                return je;
            }

            public void setJe(float je) {
                this.je = je;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
