import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {React, useContext, useState, useEffect } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { PostContext } from './PostContext';
import {Button, Card, placeholder} from 'react-bootstrap';
import axios from 'axios';


function Board() {
    let navigate = useNavigate();

    const [PhpPosts, setPhpPosts] = useState([]);
    
    useEffect(() => {
        // const fetchPosts = async () => {
        //     try {
        //         const response = await axios.get('http://sy2978.dothome.co.kr/ContentShare.php');
        //         setPhpPosts(response.data); // 서버에서 받은 데이터로 상태 업데이트
        //     } catch (error) {
        //         console.error('게시글을 불러오는데 실패함.', error);
        //     }
        // };

        // fetchPosts();
        fetch('http://sy2978.dothome.co.kr/ContentShare.php')
        .then((response) => response.json())
        .then((data) => {
        // 데이터를 상태에 저장합니다.
        console.log("data는? ", data);
        setPhpPosts(data);
        })
        .catch((error) => {
        console.error('오류뜸: ', error);
        });
    }, []);
    const allPosts = PhpPosts;

    const incrementViewCount = async (ShareID) => {
        try {
            const postData = new URLSearchParams();
            postData.append('share_id', ShareID);
            const response = await axios.post('http://sy2978.dothome.co.kr/content_share_view_up.php', postData);
            console.log("조회수 증가 응답", response.data);

        } catch (error) {
            console.error('조회수 증가 API 호출 실패', error);
        }
    }
    const handleCardClick = (ShareID) => {
        incrementViewCount(ShareID); 
        navigate(`/boardDetail/${ShareID}`);
    }

    const timeSince = (date) => {
        const postDate = new Date(date);
        const today = new Date();
        const differenceInTime = today.getTime() - postDate.getTime();
        const differenceInDays = Math.floor(differenceInTime / (1000 * 3600 * 24));
        
        if (differenceInDays === 0) {
        return '오늘';
        } else if (differenceInDays === 1) {
        return '1일 전';
        } else {
        return `${differenceInDays}일 전`;
        }
    }

    return(
        <>
        <div className='library-nav'>
            <div className='library-comment1'>
                나눔을 통해 행복을 나누다 
            </div>
            <div className='library-comment2'>
                도담덕 정보 나눔
            </div>
        </div>
        {/* {renderPosts} */}
        {/* <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center'}}> */}
        {allPosts.map((post) => (
            <div className='board-container' key = {post.ShareID}  onClick={() => handleCardClick(post.ShareID)} style={{ display: 'flex', alignItems: 'center' }}>
                <div className='board-deco'>
                    {/* <img variant="top" src={post.images ? post.images[0] : "https://static.hyundailivart.co.kr/upload_mall/board/ME00000044/B200025249/B200025249_mnImgPathFile_20210520150319893.jpeg/dims/autorotate/on"} width={'180px'} height={'130px'} style={{borderRadius: '3px'}}/> */}
                    {/* <img variant="top" src={`http://sy2978.dothome.co.kr/${post.ImageURL}`} /> */}
                    <img variant="top" src={post.ImageURL}  width={'180px'} height={'130px'} style={{borderRadius: '3px' , cursor: 'pointer'}} />
                </div>
                <div className='board-post-content' >
                <h4 style={{cursor: 'pointer'}}>{post.Title}</h4>
                <p className='sharing-card-info'>댓글 {post.CommentCount}개 ㆍ{timeSince(post.CreatedAt)}ㆍ조회 {post.Views}</p>
                <p style={{cursor: 'pointer'}}>{post.Content || post.description}</p>
                </div>
            </div>
        ))}
        {/* </div> */}
        

        <div className='circle' onClick={()=> {navigate('/BoardPost')}}>
            <FontAwesomeIcon icon={faPlus} className='plus-sign' />
        </div>
        </>
    )
}

export default Board;