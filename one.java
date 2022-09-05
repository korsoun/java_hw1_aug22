package one;

public class one {
    
    static int[] get_routes_qnt_arr(int start_pos, int finish_pos, int sum_step, int mult_step) {
        int[] routes_qnt = new int[finish_pos+1];
        routes_qnt[start_pos] = 1;

        for (int index = start_pos + sum_step; index <= finish_pos; index++) {
            if (index % mult_step == 0) {
                routes_qnt[index] = routes_qnt[index - sum_step] + routes_qnt[index / mult_step];
            } else {
                routes_qnt[index] = routes_qnt[index - sum_step];
            }
        }
        return routes_qnt;
    }

    static Boolean can_goto_finish (int[] routes_qnt, int finish_pos) {
        return routes_qnt[finish_pos] > 0? true: false;
    }

    static String get_route (int start_pos, int finish_pos, int sum_step, int mult_step) {
        int[] routes_qnt = get_routes_qnt_arr(start_pos, finish_pos, sum_step, mult_step);
        String route = "";
        int position = routes_qnt.length - 1;
        while (position > 1) {
            if (position % mult_step == 0 && routes_qnt[position / mult_step] != 0) {
                position /= mult_step;
                route += " Comm2";
                continue;
            } else {
                position -= sum_step;
                route += " Comm1";
                continue;
            }
        }
        route = route.trim();
        String[] route_arr = route.split(" ");
        int left_ind = 0;
        int rigth_ind = route_arr.length - 1;
        String temp;
        while (left_ind < route_arr.length / 2) {
            temp = route_arr[left_ind];
            route_arr[left_ind] = route_arr[rigth_ind];
            route_arr[rigth_ind] = temp;
            left_ind ++;
            rigth_ind --;
        }
        route = "";
        for(int i = 0; i < route_arr.length; i++) {
            route += route_arr[i]+" ";
        }
        return route.trim();
    }

    public static void main(String[] args) {
        int start = 1;
        int finish = 7;
        int c = 1;
        int d = 2;
        System.out.printf("Хмммм. Пройти от %d до %d, используя команды 'прибавить %d' и 'умножить на %d'.", start, finish, c, d);
        System.out.println();
        if (start < finish) {
            int[] routes_qnt_arr = get_routes_qnt_arr(start, finish, c, d);
            if (can_goto_finish(routes_qnt_arr, finish) == true) {
                System.out.printf("Возможный вариант пути: %s.", get_route(start, finish, c, d));
            } else {
                System.out.println("Путей нет.");
            }
        } else {
            System.out.println("Ой, программа не приспособлена под такие случаи.");
        }
    }
}
