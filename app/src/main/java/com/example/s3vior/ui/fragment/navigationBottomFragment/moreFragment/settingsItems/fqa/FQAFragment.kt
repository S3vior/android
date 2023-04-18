package com.example.s3vior.ui.fragment.navigationBottomFragment.moreFragment.settingsItems.fqa

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s3vior.databinding.FragmentFQABinding
import com.example.s3vior.ui.fragment.base.BaseFragment
import com.example.s3vior.ui.fragment.navigationBottomFragment.homeFragment.RecyclerViewInteractionListener

class FQAFragment : BaseFragment<FragmentFQABinding>(FragmentFQABinding::inflate)
    ,RecyclerViewInteractionListener {
    override fun callFunctions() {
        recyclerAdapter()
    }
    private fun recyclerAdapter() {
        val faqList = listOf(
            FqaContent(
                "What is a face recognition app?",
                "A face recognition app is a software application that uses artificial intelligence algorithms to analyze and compare images of people's faces in order to identify and match them."
            ),
            FqaContent(
                "How does a face recognition app work?",
                "A face recognition app uses computer vision technology to capture an image of a person's face, and then applies algorithms to analyze the unique features and patterns of that face. It then compares this data to a database of known faces in order to identify a match."
            ),
            FqaContent(
                "Can a face recognition app be used to track people without their knowledge?",
                "Yes, in some cases a face recognition app can be used to track people without their knowledge or consent. It is important to use face recognition technology ethically and responsibly, and to respect people's privacy and personal information."
            ),
            FqaContent(
                "What are some potential privacy concerns associated with face recognition apps?",
                "Some potential privacy concerns associated with face recognition apps include the collection and storage of personal data, the risk of data breaches or hacking, and the potential for misuse of the technology."
            ),
            FqaContent(
                "Are there any laws or regulations governing the use of face recognition apps?",
                "Yes, there are some laws and regulations governing the use of face recognition technology in certain contexts, such as in law enforcement or public surveillance. However, these laws vary by jurisdiction and may not cover all uses of the technology."
            ),
            FqaContent(
                "Can face recognition apps be used for discriminatory purposes?",
                "Yes, face recognition apps can be used for discriminatory purposes if they are trained on biased data or used in a biased manner. It is important to ensure that face recognition technology is used fairly and without discrimination."
            ),
            FqaContent(
                "Can face recognition apps be used for security purposes?",
                "Yes, face recognition apps can be used for security purposes such as unlocking devices or accessing secure areas. However, it is important to balance security needs with privacy concerns and to use the technology responsibly."
            ),
            FqaContent(
                "How accurate are face recognition apps?",
                "The accuracy of face recognition apps can vary depending on factors such as the quality of the image and the algorithm used. Some studies have shown that face recognition technology can be less accurate for certain groups of people, such as women and people with darker skin tones."
            ),
            FqaContent(
                "Can face recognition apps be used for social media or dating apps?",
                "Yes, some social media and dating apps use face recognition technology to help users find and connect with other people. However, it is important to use this technology responsibly and to obtain users' consent before using their personal data."
            )
        )


        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = FqaAdapter(faqList, this)

    }

    override fun <T> onClickItem(view: T) {
        view as  FqaContent
    }
}