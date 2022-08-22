//package com.codelion.animalcare.domain.medical_appointment;
//
//import org.springframework.ui.Model;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.text.DecimalFormat;
//import java.util.Calendar;
//import java.util.Map;
//
//public class ReservationCommand implements SCommand {
//
//    @Override
//    public void execute(SqlSession sqlSession, Model model, HttpSession httpSession) {
//        Map<String, Object> map = model.asMap();
//        HttpServletRequest request = (HttpServletRequest) map.get("request");
//
//        int sNo = Integer.parseInt(request.getParameter("sNo"));
//        Dto_Share detail = ReservationInfo.detail;
//        //총 이용 가능한 시간
//        int totalTime = detail.getEndTime() - detail.getStartTime();
//
//        DecimalFormat df = new DecimalFormat("00");
//        Calendar currentCalendar = Calendar.getInstance();
//        int month  = Integer.parseInt(df.format(currentCalendar.get(Calendar.MONTH) + 1));
//
//        //dto를 이번달과 다음달로 분류
//        ArrayList<Dto_Reservation_rental> thisMonthDtos = new ArrayList<Dto_Reservation_rental>();
//        ArrayList<Dto_Reservation_rental> nextMonthDtos = new ArrayList<Dto_Reservation_rental>();
//
//        // 예약일자,Dto_Reservation_rental 쌍의 LinkedHashMap : 순서를 보장한다. 정렬후 사용하므로 순서 보장이 되는쪽이 효율
//        LinkedHashMap<Integer, Dto_Refine_rental> thisMonthResMap =
//                new LinkedHashMap<Integer, Dto_Refine_rental>();
//        LinkedHashMap<Integer, Dto_Refine_rental> nextMonthResMap =
//                new LinkedHashMap<Integer, Dto_Refine_rental>();
//
//        //map을 jsonArray로 변환
//        JSONArray thisMonthResData = new JSONArray();
//        JSONArray nextMonthResData = new JSONArray();
//
//        //이용시간이 꽉 찬 경우의 일자를 따로 저장한다.
//        ArrayList<Integer> thisMonthFullDate = new ArrayList<Integer>();
//        ArrayList<Integer> nextMonthFullDate = new ArrayList<Integer>();
//
//
//        // 성공적으로 로드를 못할 때도 존재하니 no 일치하는지 검사
//        if (sNo == detail.getNo()) {
//            detail.printAll();
//
//            Dao_Reservation dao = sqlSession.getMapper(Dao_Reservation.class);
//            ArrayList<Dto_Reservation_rental> preDtos = dao.refineShares(detail.getPlace_no());
//            ArrayList<Dto_Reservation_rental> dtos = new ArrayList<Dto_Reservation_rental>();
//            for (Dto_Reservation_rental preDto : preDtos) {
//                Timestamp checkInDate = preDto.getCheckInDate();
//                System.out.println("checkInDate : " + checkInDate);
//                int startTime = preDto.getStartTime();
//                int endTime = preDto.getEndTime();
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTime(checkInDate);
//
//                int tMonth = calendar.get(Calendar.MONTH) + 1;
//                int tDate = calendar.get(Calendar.DATE);
//                int tUsingTime = endTime - startTime;
//
//                Dto_Reservation_rental uDto = new Dto_Reservation_rental(tMonth, tDate, startTime, tUsingTime);
//                System.out.println(month + " / " + tDate + " : " + startTime + " + " + tUsingTime);
//                dtos.add(uDto);
//            }
//
//            //dto를 이번달과 다음달로 분류
//            for(Dto_Reservation_rental dto : dtos) {
//                System.out.println("이번달 : " + month + "   dto의 달 : " + dto.getMonth());
//                if (dto.getMonth() == month) {
//                    thisMonthDtos.add(dto);
//                } else {
//                    nextMonthDtos.add(dto);
//                }
//            }
//
//            //각 dtos에서 예약일자 추출해 하나의 날자로 통
//            for (Dto_Reservation_rental dto : thisMonthDtos) {
//                Dto_Refine_rental refineData;
//                //예약일자가 map에 존재하지 않는다면 추가
//                if (thisMonthResMap.containsKey(dto.getDate()) == false) {
//                    //map에 넣을 dto 생성
//                    refineData = new Dto_Refine_rental();
//                } else {
//                    //이 경우는 이미 map에 존재하므로, 불러온다
//                    refineData = thisMonthResMap.get(dto.getDate());
//                }
//                refineData.insertData(detail.getStartTime(), dto.getStartTime(), dto.getUsingTime());
//                //해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
//                thisMonthResMap.put(dto.getDate(), refineData);
//            }
//
//            for (Dto_Reservation_rental dto : nextMonthDtos) {
//                Dto_Refine_rental refineData;
//                //예약일자가 map에 존재하지 않는다면 추가
//                if (nextMonthResMap.containsKey(dto.getDate()) == false) {
//                    //map에 넣을 dto 생성
//                    refineData = new Dto_Refine_rental();
//                } else {
//                    //이 경우는 이미 map에 존재하므로, 불러온다
//                    refineData = nextMonthResMap.get(dto.getDate());
//                }
//                refineData.insertData(detail.getStartTime(), dto.getStartTime(), dto.getUsingTime());
//                //해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
//                nextMonthResMap.put(dto.getDate(), refineData);
//            }
//
//            //map에서 json으로 변환
//            for(Map.Entry<Integer, Dto_Refine_rental> refineData :thisMonthResMap.entrySet()) {
//                Dto_Refine_rental dto = refineData.getValue();
//
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("date", refineData.getKey());
//                jsonObject.put("startNum", dto.getStartNum());
//                jsonObject.put("shareTime", dto.getShareTime());
//                jsonObject.put("totalShareTime", dto.getTotalShareTime());
//                thisMonthResData.add(jsonObject);
//
//                if(totalTime <= dto.getTotalShareTime()) {
//                    System.out.println("java 꽉 찬날 : " + refineData.getKey());
//                    thisMonthFullDate.add(refineData.getKey());
//                }
//            }
//
//            for(Map.Entry<Integer, Dto_Refine_rental> refineData :nextMonthResMap.entrySet()) {
//                Dto_Refine_rental dto = refineData.getValue();
//
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("date", refineData.getKey());
//                jsonObject.put("startNum", dto.getStartNum());
//                jsonObject.put("shareTime", dto.getShareTime());
//                jsonObject.put("totalShareTime", dto.getTotalShareTime());
//                nextMonthResData.add(jsonObject);
//
//                if(totalTime <= dto.getTotalShareTime()) {
//                    System.out.println("java 꽉 찬날 : " + refineData.getKey());
//                    nextMonthFullDate.add(refineData.getKey());
//                }
//            }
//
//            // share-detail 정보 dto로 담아 전송
//            model.addAttribute("DETAIL", detail);
//            // dto를 이번달과 다음달로 나눠서 전송
//            model.addAttribute("thisMonthResData", thisMonthResData);
//            model.addAttribute("nextMonthResData", nextMonthResData);
//
//            Integer[] thisMonthFullDateList = thisMonthFullDate.toArray(new Integer[thisMonthFullDate.size()]);
//            Integer[] nextMonthFullDateList = nextMonthFullDate.toArray(new Integer[nextMonthFullDate.size()]);
//
//            model.addAttribute("thisMonthFullDateList", thisMonthFullDateList);
//            model.addAttribute("nextMonthFullDateList", nextMonthFullDateList);
//            model.addAttribute("error", 0);
//            System.out.println("place no이 동일하므로 다음작업을 수행합니다.");
//        } else {
//            System.out.println("시스템 오류. 데이터를 로드하는데 문제가 발생했습니다.");
//            //error 전송
//            model.addAttribute("error", 1);
//        }
//    }
//}
