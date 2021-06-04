import styled from "styled-components";
import { useReducer, MouseEvent } from "react";
import Calendar from "./Calendar";
import { FaChevronRight, FaChevronLeft } from "react-icons/fa";


interface CalendarSliderType {
  CalendarType: "SINGLE" | "DOUBLE";
}

interface dateType {
  year: number;
  mon: number;
}

const CalendarSlider = ({ CalendarType }: CalendarSliderType) => {
  let nowDate: dateType[];

  type Action = { type: "MON_INCREASE" } | { type: "MON_DECREASE" };

  const getDeepCopy = (original: any) => JSON.parse(JSON.stringify(original));

  const calendarReducer = (state: dateType, action: Action) => {
    const deepCopied = getDeepCopy(state);
    switch (action.type) {
      case "MON_INCREASE":
        deepCopied.mon += 1;
        if (deepCopied.mon > 12) {
          deepCopied.mon = 1;
          deepCopied.year += 1;
        }
        return deepCopied;
      case "MON_DECREASE":
        deepCopied.mon -= 1;
        if (deepCopied.mon < 1) {
          deepCopied.mon = 12;
          deepCopied.year -= 1;
        }
        return deepCopied;
    }
  };

  const [date, dateDispatch] = useReducer(calendarReducer, {
    year: new Date().getFullYear(),
    mon: new Date().getMonth() + 1,
  });

  const secondDate = () => {
    const deepCopied = getDeepCopy(date);
    deepCopied.mon += 1;
    if (deepCopied.mon > 12) {
      deepCopied.mon = 1;
      deepCopied.year += 1;
    }
    return deepCopied;
  };

  const handleLeftClick = (e: MouseEvent) => {
    dateDispatch({ type: "MON_DECREASE" });
  };

  const handleRightClick = (e: MouseEvent) => {
    dateDispatch({ type: "MON_INCREASE" });
  };
  if (CalendarType === "DOUBLE") {
    nowDate = [date, secondDate()];
  } else {
    nowDate = [date];
  }

  return (
    <StyledCalendarSlider>
      <CalendarSliderLeftBtn onClick={handleLeftClick}>
        <FaChevronLeft />
      </CalendarSliderLeftBtn>
      {nowDate.map((v, i) => (
        <Calendar date={v} key={`cal${i}`} />
      ))}
      <CalendarSliderRightBtn onClick={handleRightClick}>
        <FaChevronRight />
      </CalendarSliderRightBtn>
    </StyledCalendarSlider>
  );
};

export default CalendarSlider;
const StyledCalendarSlider = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
`;
const CalendarSliderLeftBtn = styled.button`
  position: absolute;
  top: 2.5rem;
  background-color: transparent;
  left: 4rem;
  font-size: 1.4rem;
`;
const CalendarSliderRightBtn = styled.button`
  position: absolute;
  top: 2.5rem;
  background-color: transparent;
  right: 4rem;
  font-size: 1.4rem;
`;