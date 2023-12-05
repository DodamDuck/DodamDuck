import { Container, Card, Button, Form } from "react-bootstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPaperPlane } from '@fortawesome/free-solid-svg-icons';
import React, { useState, useEffect } from 'react';
import axios from "axios";
import { useAuth } from './AuthContext';
import { useParams } from 'react-router-dom';


function ChattingDetail() {
    const [chatList, setChatList] = useState([]);
    const [messages, setMessages] = useState([]);
    const { id } = useParams();
    const { partnerID } = useParams();
    const { partnerName } = useParams();
    const { user } = useAuth();

    const mostRecentMessage = messages[messages.length - 1];

    useEffect(() => {
        if (!user) {
            console.log("User is not defined, skipping API call.");
            return; // isLoading 상태를 관리할 필요가 없다면 이 줄을 생략할 수 있습니다.
        }
        console.log("id는 ", id);
        console.log("partnerID는 ", partnerID);
        console.log("partnerName는 ", partnerName);
        axios.get(`http://sy2978.dothome.co.kr/get_chat_list.php?user_id=${user.userID}`)
        
            .then(response => {
                if (response.data && Array.isArray(response.data.chat_list)) {
                    setChatList(response.data.chat_list);
                } else {
                    console.log('No chat list available');
                }
            })
            .catch(error => {
                console.error('채팅 목록 요청 실패:', error);
            });
    }, [user]);

    useEffect(() => {
        if (!user) {
            console.log("User is not defined, skipping API call.");
            return;
        }
        // axios.get(`http://sy2978.dothome.co.kr/getMessage.php?user1=${user.userID}&user2=${partnerID}`)
        axios.get(`http://sy2978.dothome.co.kr/getMessage.php?user1=${user.userID}&user2=${partnerID}`)
            .then(response => {
                console.log('response.data는 ? ', response.data);
                console.log('response.data는 ? ', response.data[0].receiverID);
                if (response.data) {
                console.log('response.data는 ?? ', response.data);
                    setMessages(response.data);
                } else {
                    console.log('No messages available');
                }
            })
            .catch(error => {
                console.error('메시지 목록 요청 실패:', error);
            });
    }, [user, id]);

    
    if (!user) {
        return <div>로딩 중...</div>;
    }
    return (
        <>
        <div className="chat-container">
            <div style={{margin: '20px', display: 'flex', flexDirection: 'column'}}>
                    
                
                <div style={{display: 'flex', justifyContent: 'center'}}>
                <img src={user.profile_url || "https://www.lab2050.org/common/img/default_profile.png"} width={'80px'} height={'80px'} style={{borderRadius: '50%'}}/>
                    <h4 style={{marginRight: '15px', marginTop: '20px', marginLeft: '10px'}}>{user.userName} 님</h4>
                    <h7 style={{marginTop: '20px'}} className="chat-user-level">level.{user.level}</h7>
                </div>

                <h6 style={{marginTop: '30px', color: '#303030'}}>
                    채팅 중인 이웃
                </h6>
                {chatList.map((chat, index) => (
                    <div className="chat-user-line" key={chat.chat_id || index}>
                        <div style={{ display: 'flex', marginTop: '7px', marginBottom: '7px' }}>
                            <img src={`http://sy2978.dothome.co.kr/userProfile/user_id_${chat.user1_id === user.userID ? chat.user2_id : chat.user1_id}.jpg`} width={'72px'} height={'72px'} style={{ borderRadius: '50%' }} />
                            <div style={{ flexDirection: 'column' }}>
                                <h6 style={{ marginRight: '15px', marginTop: '20px', marginLeft: '10px', cursor: 'pointer' }}>
                                    {chat.user1_id === user.userID ? chat.user2_name : chat.user1_name}     
                                </h6>
                                <h6 className='myshop-level' style={{ marginTop: '0px', marginLeft: '10px', color: '#464646', fontSize: 'small' }}>
                                    {chat.last_message}
                                </h6>
                            </div>
                        </div>
                    </div>
                ))}

            </div>

            <div className="chat-line" >

            </div>
                {/* <div style={{flexDirection: 'column', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                <FontAwesomeIcon icon={faComments}  style={{color: "#d6d6d6", fontSize: "95px", marginLeft: '275px' }} />
                    <p className="recent-chat-comment" style={{display: 'flex', justifyContent: 'center', textAlign: 'center', alignItems: 'center', marginLeft: '270px', marginTop: '15px'}}>채팅할 상대를 선택해주세요</p>
                </div> */}
            
            <div style={{margin: '20px', display: 'flex', flexDirection: 'column'}}>
                <div style={{display: 'flex', marginTop: '7px', marginBottom: '7px'}}>
                {/* <img src={`http://sy2978.dothome.co.kr/userProfile/user_id_${user1_id === user.userID ? user2_id : user1_id}.jpg`} width={'72px'} height={'72px'} style={{ borderRadius: '50%' }} />                 */}
                <img src={`http://sy2978.dothome.co.kr/userProfile/user_id_${partnerID}.jpg`} width={'72px'} height={'72px'} style={{ borderRadius: '50%' }} />                
                    <h6 style={{marginRight: '15px', marginTop: '20px', marginLeft: '10px'}}>{partnerName}</h6>
                    <h6 style={{marginTop: '20px', color: '#FFD600'}} className="myshop-level">level.4</h6>
                </div>
            
                {/* <div className="chat2-user-line">
                    <div style={{display: 'flex', marginTop: '7px', marginBottom: '7px'}}>
                    <img src="https://wafuu.com/cdn/shop/products/sanrio-official-cinnamoroll-baby-care-set-512991-plush-toy-doll-185313.jpg?v=1695256528" width={'72px'} height={'72px'} style={{borderRadius: '5%'}}/>
                        <div style={{ flexDirection: 'column'}}>
                            <h6 style={{marginRight: '15px', marginTop: '20px', marginLeft: '10px', fontSize: 'small' }}>시나모롤 인형세트</h6>
                            <h6 className='myshop-level chat-radio' style={{marginTop: '0px', marginLeft: '10px' , fontSize: 'small'}}>교환</h6>
                        </div>
                    </div>
                </div> */}
            
                
                
                <div className="chat2-user-line">
                    {/* <p className='chat-date' style={{marginTop: '15px'}}>2023년 11월 07일</p> */}
                    {/* {messages.map((message, index) => ( */}
                        {/* <> */}
                    <p className='chat-date' style={{marginTop: '15px'}}>{new Date(mostRecentMessage?.timestamp).toLocaleDateString('ko-KR', {
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric'
                    })}</p>
                    {/* <div style={{display: 'flex', marginTop: '7px', marginBottom: '7px'}}>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqIArEc23xr8KUpAm1yS6vPXjtg__1D5RvSQ&usqp=CAU" width={'47px'} height={'47px'} style={{borderRadius: '50%'}}/>
                        <div style={{ flexDirection: 'column'}}>
                            <h6 className='real-chat' style={{marginRight: '15px', marginTop: '24px', marginLeft: '10px', fontSize: 'small' }}>{mostRecentMessage?.message}</h6>
                        </div>
                        
                    </div> */}
                    {/* </> */}
                {/* ))} */}
                {messages.map((message, index) => (
                    <div key={message.id} style={{ display: 'flex', justifyContent: message.senderID === user.userID ? 'flex-end' : 'flex-start', marginTop: '7px', marginBottom: '7px' }}>
                    {message.senderID === user.userID ? (
                            <>
                            {/* 사용자 본인의 메시지 표시 */}
                            <div style={{ display: 'flex', flexDirection: 'row-reverse', alignItems: 'center', justifyContent: 'flex-end' }}>
                                <h6 className='real-chat-me' style={{ fontSize: 'small' }}>
                                {message.message}
                                </h6>
                                {/* <img src={`http://sy2978.dothome.co.kr/userProfile/user_id_${user.userID}.jpg`} width={'47px'} height={'47px'} style={{ borderRadius: '50%', marginLeft: '10px' }} /> */}
                            </div>
                            </>
                        ) : (
                            <>
                            {/* 상대방에 의해 보내진 메시지 표시 */}
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                <img src={`http://sy2978.dothome.co.kr/userProfile/user_id_${message.senderID}.jpg`} width={'47px'} height={'47px'} style={{ borderRadius: '50%', marginRight: '10px' }} />
                                <h6 className='real-chat' style={{ fontSize: 'small' }}>
                                {message.message}
                                </h6>
                            </div>
                            </>
                        )}
                        </div>
                ))}
                    
                    {/* <div style={{display: 'flex', marginTop: '9px', marginBottom: '9px'}}>
                            <h6 className='real-chat-me' >네 됩니다.</h6>
                    </div>
                    <div style={{display: 'flex', marginTop: '9px', marginBottom: '9px'}}>
                            <h6 className='real-chat-me' >내일도 됩니다~</h6>
                    </div>
                     */}
                    <div style={{ display: 'flex'}}>
                    <Form.Control type="text" placeholder="메시지를 입력해주세요." className="chat-ready"/> 
                        <FontAwesomeIcon icon={faPaperPlane} className="chat-paper-plane" style={{color: "#dcdcdc"}} />
                    </div>
                </div>

            </div>
            
        </div>
        </>
    )
}

export default ChattingDetail;