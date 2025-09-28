import {   Table   } from "reactstrap"

export default function DeveloperList() {
    const developers = [
        { name: "Stu Mackenzie", email: "kglw@gmail.com", url:"kglw.com", picUrl:"https://cdn.getmidnight.com/f655f096d6a1f6fbcbed5d050759ab54/2025/06/stu-mackenzie-king-gizzard-and-the-lizard-wizard-getty-images-joseph-okpako.jpg" },
        {name: "Kirby", email: "poyo@gmail.com", url: "poyo.com", picUrl:"https://static.wikia.nocookie.net/kirby/images/8/86/KRtDLD_Kirby.png/revision/latest?cb=20230214153003"}
    ]

    const developerList = developers.map((d) => {
        return (
            <tr key = {d.id}>
                <td className="text-center">{d.name}</td>
                <td className="text-center">{d.email}</td>
                <td className="text-center"><a href={d.url}>{d.url}</a></td>
                <td className="text-center"><img src={d.picUrl} alt={d.name} width="50px"/></td>
            </tr>
        )
    });

    return (
        <div>
            <div className="admin-page-container">
                <h1 className="text-center">Developers!</h1>
                <div>
                    <Table aria-label="developers" className="mt-4">
                        <thead>
                            <tr>
                                <th className="text-center">Name</th>
                                <th className="text-center">Email</th>
                                <th className="text-center">URL</th>
                                <th className="text-center">Picture</th>
                            </tr>
                        </thead>
                        <tbody>{developerList}</tbody>
                    </Table>
                </div>
            </div>
        </div>
    )
    
}
