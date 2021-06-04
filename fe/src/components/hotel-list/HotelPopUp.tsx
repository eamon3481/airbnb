import { Box } from "@material-ui/core";
import styled from "styled-components";
import HotelPopUpItem from "./HotelPopUpItem";
import { MouseEvent } from "react";
import { useRecoilValue, useResetRecoilState } from "recoil";
import {
  CalendarData,
  PeopleDataSelector,
  popUpState,
} from "atoms/searchbarAtom";
const HotelPopUp = () => {
  const popup = useRecoilValue(popUpState);
  const resetPopUp = useResetRecoilState(popUpState);

  const [checkInData, checkOutData] = useRecoilValue(CalendarData);
  const peopleValue = useRecoilValue(PeopleDataSelector);
  const dateparser = (date: number) =>
    new Date(date).toISOString().slice(0, 10).replace(/[-]/gi, ". ");
  const clickHandler = (e: MouseEvent<HTMLElement>) => {
    const target = e.target as HTMLElement;
    if (target.id === "modal") resetPopUp();
  };
  return (
    <StyledHotelDetail id="modal" onClick={clickHandler}>
      <Modal>
        <Box width="80%" fontSize="1.2rem">
          <Prcie>₩ {popup.price} </Prcie> /박
        </Box>
        <StyledReservationData>
          <Box width="100%" borderBottom="1px solid gray" display="flex">
            <Box width="50%" borderRight="1px solid gray">
              <HotelPopUpItem
                title="체크인"
                subtitle={dateparser(checkInData)}
              />
            </Box>
            <Box width="50%">
              <HotelPopUpItem
                title="체크아웃"
                subtitle={dateparser(checkOutData)}
              />
            </Box>
          </Box>

          <Box width="100%">
            <HotelPopUpItem title="인원" subtitle={peopleValue} />
          </Box>
        </StyledReservationData>
        <ReservationBtn>예약하기</ReservationBtn>
        <Subtitle>예약 확전 전에는 요금이 청구되지 않습니다.</Subtitle>
      </Modal>
    </StyledHotelDetail>
  );
};

export default HotelPopUp;

const Prcie = styled.span`
  font-size: 2rem;
  font-weight: 700;
`;

const StyledHotelDetail = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999999;
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.5);
`;
const StyledReservationData = styled.div`
  border: ${({ theme }) => theme.border.Gray4};
  border-radius: 1rem;
  display: flex;
  flex-direction: column;
  width: 80%;
  margin: 2rem 0;
`;

const Subtitle = styled.span`
  font-weight: 400;
  font-size: 0.8rem;
  color: ${({ theme }) => theme.color.Gray3};
`;
const Modal = styled.div`
  width: 35rem;
  height: 45rem;
  border-radius: 3rem;
  background-color: white;
  display: flex;
  padding: 2rem 0;
  flex-direction: column;
  align-items: center;
`;

const ReservationBtn = styled.button`
  background-color: #333333;
  color: white;
  width: 80%;
  height: 4rem;
  border-radius: 1rem;
  font-size: 2rem;
  font-weight: 700;
  margin: 1rem 0;
`;