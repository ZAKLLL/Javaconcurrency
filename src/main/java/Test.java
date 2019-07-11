import java.util.ArrayList;
import java.util.List;

/**
 * @program: javaconcurrency
 * @description:
 * @author: ZakL
 * @create: 2019-05-05 10:35
 **/

public class Test {


    public static List<Dept> getSonDepts(Dept dept) {
        List<Dept> pDepts = new ArrayList<>();
        pDepts.add(dept);
        List<Dept> sDepts = new ArrayList<>();
        for (int i = 0; i < pDepts.size(); i++) {
            List<Dept> sonDeptsById = getSonDeptsById(pDepts.get(i).getId());
            pDepts.get(i).setSonDept(sonDeptsById);
            sDepts.addAll(sonDeptsById);
            if (sonDeptsById.size() > 0) {
                for (Dept sDept : sonDeptsById) {
                    sDept.setSonDept(getSonDeptsById(sDept.getId()));
                    pDepts.addAll(getSonDeptsById(sDept.getId()));
                }
            }
        }
        List<Dept> result = new ArrayList<>();
        result.addAll(pDepts);
        result.addAll(sDepts);
        return result;
    }


    public static void main(String[] args) {
        getSonDepts(new Dept(2L, "D0", 0L)).get(4).getSonDept().forEach(System.out::println);
    }

    static List<Dept> depts = new ArrayList<>();

    static {
        depts.add(new Dept(0L, "d0", 1000L));
        depts.add(new Dept(1L, "d1", 0L));
        depts.add(new Dept(2L, "d2", 0L));
        depts.add(new Dept(3L, "d3", 1L));
        depts.add(new Dept(4L, "d4", 1L));
        depts.add(new Dept(5L, "d5", 2L));
        depts.add(new Dept(6L, "d6", 1L));
        depts.add(new Dept(7L, "d7", 5L));
        depts.add(new Dept(8L, "d8", 5L));
        depts.add(new Dept(9L, "d9", 2L));
        depts.add(new Dept(10L, "d10", 5L));
        depts.add(new Dept(11L, "d11", 7L));
        depts.add(new Dept(12L, "d12", 2L));
        depts.add(new Dept(13L, "d13", 2L));
    }

    private static List<Dept> getSonDeptsById(Long deptid) {
        List<Dept> depts2 = new ArrayList<>();
        for (Dept d : depts) {
            if (d.pid.equals(deptid)) {
                depts2.add(d);
            }
        }
        //模拟dao
        return depts2;
    }


    private static class Dept {
        private Long id;
        private String desc;
        private Long pid;
        private List<Dept> sonDept;

        public List<Dept> getSonDept() {
            return sonDept;
        }

        public void setSonDept(List<Dept> sonDept) {
            this.sonDept = sonDept;
        }

        public Long getId() {
            return id;
        }


        @Override
        public String toString() {
            return "Dept{" +
                    "id=" + id +
                    ", desc='" + desc + '\'' +
                    ", pid=" + pid +
                    '}';
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public Long getPid() {
            return pid;
        }

        public void setPid(Long pid) {
            this.pid = pid;
        }

        public Dept(Long id, String desc, Long pid) {
            this.id = id;
            this.desc = desc;
            this.pid = pid;
        }
    }


}
